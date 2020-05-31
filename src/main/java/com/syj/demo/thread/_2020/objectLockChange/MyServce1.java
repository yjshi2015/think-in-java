package com.syj.demo.thread._2020.objectLockChange;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/5/31.
 */
public class MyServce1 {

    public void methodTest(UserInfo1 userInfo1) {
        synchronized (userInfo1) {
            try {
                System.out.println("Thread name = " + Thread.currentThread().getName() + " begin");
                userInfo1.setUserName("basdfd");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Thread name = " + Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
