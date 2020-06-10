package com.syj.demo.thread._2020.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/10.
 */
public class MyService {

    private Lock lock = new ReentrantLock();

    public void methodA() {
        try {
            lock.lock();
            System.out.println("methodA begin ThreadName " + Thread.currentThread().getName() + " time " + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
            System.out.println("methodA end   ThreadName " + Thread.currentThread().getName() + " time " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        try {
            lock.lock();
            System.out.println("methodB begin ThreadName " + Thread.currentThread().getName() + " time " + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
            System.out.println("methodB end   ThreadName " + Thread.currentThread().getName() + " time " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
