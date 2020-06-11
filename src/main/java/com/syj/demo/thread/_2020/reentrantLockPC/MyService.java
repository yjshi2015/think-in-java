package com.syj.demo.thread._2020.reentrantLockPC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/11.
 */
public class MyService {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void produce() {
        try {
            lock.lock();
            while (hasValue == true) {
                System.out.println(Thread.currentThread().getName() + ",有库存，无需生产，生产线程等待，消费线程启动");
                // 注意，不是wait()方法，wait是object类的方法，依赖notify
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ",无库存，开始生产，生产线程启动");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        try {
            lock.lock();
            while (hasValue == false) {
                System.out.println(Thread.currentThread().getName() + ",无库存，需要生产，消费线程等待，生产线程启动");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + ",有库存，开始消费，消费线程启动");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
