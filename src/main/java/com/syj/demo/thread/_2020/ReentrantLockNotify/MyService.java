package com.syj.demo.thread._2020.ReentrantLockNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/10.
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println(" await时间为 " + System.currentTimeMillis());
            condition.await();
            System.out.println(" await锁在 await后面释放掉了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(" await锁在final里释放掉了");
        }

    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal开始通知时间为 " + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
            System.out.println("signal释放锁时间时间为 " + System.currentTimeMillis());
        }

    }
}
