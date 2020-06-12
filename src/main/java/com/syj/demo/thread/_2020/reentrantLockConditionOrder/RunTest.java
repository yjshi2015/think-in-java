package com.syj.demo.thread._2020.reentrantLockConditionOrder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2020/6/12.
 *
 * 使用condition对象可以对线程的执行业务进行排序规划
 */
public class RunTest {

    volatile public static int nextPrintWho = 1;
    private static ReentrantLock lock = new ReentrantLock();
    final static Condition conditionA = lock.newCondition();
    final static Condition conditionB = lock.newCondition();
    final static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 1) {
                    conditionA.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("t1 " + (i + 1));
                }
                nextPrintWho = 2;
                conditionB.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.setName("t1");

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 2) {
                    conditionB.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("t2 " + (i + 1));
                }
                nextPrintWho = 3;
                conditionC.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t2.setName("t2");

        Thread t3 = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 3) {
                    conditionC.await();
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("t3 " + (i + 1));
                }
                nextPrintWho = 1;
                conditionA.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t3.setName("t3");

        Thread[] aArr = new Thread[5];
        Thread[] bArr = new Thread[5];
        Thread[] cArr = new Thread[5];
        for (int i = 0; i < 5; i++) {
            aArr[i] = new Thread(t1);
            bArr[i] = new Thread(t2);
            cArr[i] = new Thread(t3);

            aArr[i].start();
            bArr[i].start();
            cArr[i].start();
        }
    }
}
