package com.syj.demo.thread._2020.deadLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/5/31.
 */
public class DeadThread implements Runnable {

    public String userName;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        if (userName.equals("a")) {
            // 持有锁1，但尝试获取锁2
            synchronized (lock1) {
                try {
                    System.out.println("userName = " + userName);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按照lock1->lock2代码顺序执行了");
                }
            }
        }

        if (userName.equals("b")) {
            // 持有锁2，但尝试获取锁1
            synchronized (lock2) {
                try {
                    System.out.println("username = " + userName);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("按lock2->lock1代码顺序执行了");
                }
            }
        }
    }
}
