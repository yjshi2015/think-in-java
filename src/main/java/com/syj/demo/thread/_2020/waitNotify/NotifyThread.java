package com.syj.demo.thread._2020.waitNotify;

/**
 * Created by Administrator on 2020/6/3.
 */
public class NotifyThread extends Thread{

    private Object lock;

    public NotifyThread(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("notify 开始");
            lock.notify();
            System.out.println("notify 结束");
        }
    }
}
