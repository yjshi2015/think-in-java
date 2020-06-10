package com.syj.demo.thread._2020.ReentrantLockNotify;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/10.
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        Thread t1 = new Thread(() -> myService.await());
        t1.setName("t1");
        t1.start();

        TimeUnit.MILLISECONDS.sleep(3000);
        myService.signal();
    }
}
