package com.syj.demo.thread._2020.reentrantLockTryLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/12.
 */
public class MyService1 {

    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 拿到了锁, time = " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(10);
            } else {
                System.out.println(Thread.currentThread().getName() + " 没有拿到锁, time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 当前线程持有该锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
