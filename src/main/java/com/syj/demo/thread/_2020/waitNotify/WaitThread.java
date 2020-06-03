package com.syj.demo.thread._2020.waitNotify;

/**
 * Created by Administrator on 2020/6/3.
 */
public class WaitThread extends Thread {

    private Object lock;

    public WaitThread(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("wait 开始");
                lock.wait();
                System.out.println("wait 结束");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
