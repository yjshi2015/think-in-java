package com.syj.demo.thread._2020_kaitao.async;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/20.
 *
 * 这里描述的是任务0/1/2之间并行执行，任务3阻塞汇聚最终结果
 *
 * CompletableFuture实现了CompletionStage接口，这个接口定义了任务之间的时序关系，比如串行、并行、汇聚等
 * 串行：主要是thenApply、thenAccept、thenRun和thenCompose这4个系列的接口。
 *  thenApply系列函数里参数fn的类型是接口Function<T, R>, 这个接口里与CompletableStage相关的方法是R apply(T t),
 *  这个方法既能接收参数也支持返回值，所以thenApply系列方法返回的是CompletionStage<R>
 *
 *  thenAccept系列方法里参数consumer的类型是接口Consumer<T>,这个接口里与CompletionState相关的方法是void accept(T t),
 *  这个方法虽然支持参数，但却不支持返回值，所以thenAccept系列方法返回的是CompletionStage<void>
 *
 *  thenRun系列方法里action的参数是Runnable，所以action既不能接收参数，也不能返回值，所以thenRun系列方法
 *  返回的也是CompletionState<Void>
 *
 *  这些方法里Async代表的是异步执行function、consumer、action
 *
 * AND汇聚关系：主要是thenCombine、thenAcceptBoth、runAfterBoth系列的接口，这些接口的区别也是源自function、
 *  consumer、action这3个核心参数不同。
 *
 * OR汇聚关系：主要是applyToEither、acceptEither、runAfterEither系列的接口，这些接口的区别也是源自function、
 *  consumer、action这3个核心参数不同。
 *
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) {
        // 默认情况下，CompletableFuture会使用公共的ForkJoinPool线程池，这个线程池默认创建的线程数
        // 是CPU的核数。如果所有CompletableFuture共享一个线程池，那么一旦有任务执行一些很慢的IO操作
        // 就会导致线程池中所有的线程都阻塞在IO操作上，从而造成线程饥饿，进而影响这个系统的性能。
        // 所以，建议根据不同的业务类型创建不同的线程池，避免相互干扰。
        ExecutorService ioPool = Executors.newFixedThreadPool(4);
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // runAsync()和supplyAsync()的区别，前者无返回值，后者有返回值，但都是异步执行
        CompletableFuture future0 = CompletableFuture.runAsync(() -> {
            sleep(TimeUnit.SECONDS, 1);
            System.out.println("任务零处理完成, time:" + System.currentTimeMillis());
        });

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(TimeUnit.SECONDS, 2);
            System.out.println("任务一处理完成, time:" + System.currentTimeMillis());
            return "库里1个亿";
        }, ioPool);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(TimeUnit.SECONDS, 5);
            System.out.println("任务二处理完成, time:" + System.currentTimeMillis());
            return 3;
        }, pool);

        CompletableFuture<String> future3 = CompletableFuture
                .allOf(future0, future1, future2)
                // 此处从thenApplyAsync()改为thenApply(),并无卵用，如果不加join()，依旧是异步执行
                .thenApply((Void) -> {
            Object f0Res = null;
            String f1Res = null;
            Integer f2Res = null;
            try {
                f0Res = future0.get();
                if (Objects.isNull(f0Res)) {
                    f0Res = "null";
                }
                f1Res = future1.get();
                f2Res = future2.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            sleep(TimeUnit.SECONDS, 10);
            System.out.println("任务三处理完成, time:"+System.currentTimeMillis()
                    + ",集成了任务0、1和2的结果，f0Res ：" + f0Res + ", f1Res : " + f1Res + ", f2Res : " + f2Res);
            return "成功啦";
        }).exceptionally(e -> {
            e.printStackTrace();
            return "jj";
        });

        // 通过join来阻塞主线程
        future3.join();
        System.out.println("任务三的返回值, time:" + System.currentTimeMillis());
        System.out.println(" main线程结束, time:" + System.currentTimeMillis() + ", 阻塞");
    }

    public static void sleep(TimeUnit unit, int time) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
