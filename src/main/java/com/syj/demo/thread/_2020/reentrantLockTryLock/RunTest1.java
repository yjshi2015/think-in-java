package com.syj.demo.thread._2020.reentrantLockTryLock;

/**
 * Created by Administrator on 2020/6/12.
 * <p>
 * tryLock()的作用是，仅在调用时锁定未被其他线程保持的情况下，才能获取该锁定
 *
 * tryLock(timeout, unit)在给定时间内锁未被其他线程保持，且当前线程未被中断，则获取该锁
 */
public class RunTest1 {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 调用waitMethod时间time = " + System.currentTimeMillis());
            myService.waitMethod();
        });
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 调用waitMethod时间time = " + System.currentTimeMillis());
            myService.waitMethod();
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
