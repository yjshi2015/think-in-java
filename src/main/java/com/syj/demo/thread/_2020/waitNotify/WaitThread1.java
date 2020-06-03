package com.syj.demo.thread._2020.waitNotify;

/**
 * Created by Administrator on 2020/6/3.
 */
public class WaitThread1 extends Thread {

    private Object lock;

    public WaitThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                if (MyList.size() != 5) {
                    System.out.println("wait begin");
                    lock.wait();
                    System.out.println("wait end");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
