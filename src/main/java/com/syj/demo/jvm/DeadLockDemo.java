package com.syj.demo.jvm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发生死锁的demo,验证Linux下如何获取虚拟机线程栈
 */
public class DeadLockDemo {

    public static void main(String[] args) throws Exception{

        // L1 L2阶段公用的线程池
        ExecutorService es = Executors.newFixedThreadPool(2);

        // L1阶段的闭锁
        CountDownLatch l1 = new CountDownLatch(2);

        for(int i=0; i<2; i++) {
            System.out.println("L1");
            // 执行L1阶段任务
            es.execute(() -> {
                // L2阶段的闭锁
                CountDownLatch l2 = new CountDownLatch(2);
                // 执行L2阶段的子任务
                for (int j=0; j<2; j++) {
                    es.execute(() -> {
                        System.out.println("L2");
                        l2.countDown();
                    });
                }

                // 等待L2阶段任务执行完成
                try {
                    l2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                l1.countDown();
            });
        }

        // 等待L1阶段任务执行完成
        l1.await();
        System.out.println("END");
    }

}
