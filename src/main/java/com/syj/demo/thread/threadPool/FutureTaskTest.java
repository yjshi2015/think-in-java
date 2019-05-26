package com.syj.demo.thread.threadPool;

import java.util.concurrent.*;

/**
 * FutureTask实现了Runnable接口和Future接口,有余实现了Runnable接口,所以可以将FutureTask对象作为任务提交给线程池,
 * 也可以直接被Thread执行;又因为实现了Future接口,所以也能用来获取任务的执行结果.
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建futureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {return (1+2);});
        //创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //提交任务
        pool.submit(futureTask);
        Thread.sleep(3000);
        //获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);

        pool.shutdown();

    }
}
