package com.syj.demo.thread._2020.waitNotify;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/3.
 */
public class NotifyThread1 extends Thread {

    private Object lock;

    public NotifyThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i=0; i<10; i++) {
                    MyList.add();
                    if (MyList.size() == 5) {
                        lock.notifyAll();
                        System.out.println("已发出通知");
                    }
                    System.out.println("添加了" + (i+1) + "个元素");
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
