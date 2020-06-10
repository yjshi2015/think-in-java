package com.syj.demo.thread._2020.waitNotifyAll;

/**
 * @Description 唤醒线程
 * @Date 2020/6/6 20:20
 * @Created by shiyongjun1
 */
public class NotifyAllThread extends Thread {

    private Object lock;
    public NotifyAllThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
