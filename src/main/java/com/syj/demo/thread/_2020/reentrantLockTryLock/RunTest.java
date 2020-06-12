package com.syj.demo.thread._2020.reentrantLockTryLock;

/**
 * Created by Administrator on 2020/6/12.
 *
 * tryLock()的作用是，仅在调用时锁定未被其他线程保持的情况下，才能获取该锁定
 */
public class RunTest {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.waitMethod());
        Thread t2 = new Thread(() -> myService.waitMethod());
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
