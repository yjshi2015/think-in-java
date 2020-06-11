package com.syj.demo.thread._2020.reentrantLockFair;

/**
 * Created by Administrator on 2020/6/11.
 *
 * 锁Lock分为“公平锁”和“非公平锁”，
 * 公平锁表示线程获取锁的顺序按照线程加锁的顺序来分配，即先来先得的FIFO先进先出顺序。
 * 非公平锁就是先来的不一定先得到锁，这个方式可能造成某些线程一直拿不到锁，结果就是不公平的了
 *
 */
public class RunTest {

    public static void main(String[] args) {
        // 改为true就是公平锁
        MyService myService = new MyService(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "运行了");
                myService.methodTest();
            }
        };
        Thread[] threadArr = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArr[i] = new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threadArr[i].start();
        }
    }
}
