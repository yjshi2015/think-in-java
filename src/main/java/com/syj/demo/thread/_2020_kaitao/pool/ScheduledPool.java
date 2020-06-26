package com.syj.demo.thread._2020_kaitao.pool;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2020/6/26.
 */
public class ScheduledPool {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);

        // 延迟的策略，可参见ScheduledExecutorService的schedule()方法
        // 延迟3秒后执行
//        ScheduledFuture future1 = scheduledPool.schedule(() -> {
//            try {
//                System.out.println("f1线程执行开始时间，time：" + System.currentTimeMillis());
//                TimeUnit.SECONDS.sleep(1);
//                System.out.println("f1线程执行结束时间，time：" + System.currentTimeMillis());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, 3, TimeUnit.SECONDS);
//
//        System.out.println("主线程开始执行时间，time：" + System.currentTimeMillis());
//        future1.get();


        // 延迟3秒后执行，带返回值
        ScheduledFuture<String> future2 = scheduledPool.schedule(() -> {
            try {
                System.out.println("  线程执行开始时间，time：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("  线程执行结束时间，time：" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
                return "false";
            }
            return "success";
        }, 3, TimeUnit.SECONDS);
        System.out.println("主线程执行开始时间，time：" + System.currentTimeMillis());
        String result2 = future2.get();
        System.out.println("主线程执行结束时间，time：" + System.currentTimeMillis() + ", result:" + result2);
    }
}
