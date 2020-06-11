package com.syj.demo.thread._2020.reentrantLockFair;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/11.
 */
public class MyService {

    private ReentrantLock lock;
    public MyService(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void methodTest() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "获取到锁");
        } finally {
            lock.unlock();
        }
    }
}
