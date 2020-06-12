package com.syj.demo.thread._2020.reentrantLockTryLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/12.
 */
public class MyService {

    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        if (lock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + " 拿到了锁");
        } else {
            System.out.println(Thread.currentThread().getName() + " 没有拿到锁");
        }
    }
}
