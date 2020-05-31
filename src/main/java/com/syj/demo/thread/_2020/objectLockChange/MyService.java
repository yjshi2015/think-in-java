package com.syj.demo.thread._2020.objectLockChange;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/5/31.
 */
public class MyService {

    private String lock = "123";

    public void testMethod() {
        try {
            synchronized (lock) {
                System.out.println("Thread name = " + Thread.currentThread().getName() + " begin ");
                lock = "456";
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Thread name = " + Thread.currentThread().getName() + " end ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
