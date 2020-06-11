package com.syj.demo.thread._2020.reentrantLockCondition;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/11.
 *
 * 使用condition实现通知部分线程
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.awaitA());
        t1.setName("t1");
        Thread t2 = new Thread(() -> myService.awaitB());
        t2.setName("t2");
        t1.start();
        t2.start();

        TimeUnit.MILLISECONDS.sleep(500);
        myService.signalAll_B();
    }
}
