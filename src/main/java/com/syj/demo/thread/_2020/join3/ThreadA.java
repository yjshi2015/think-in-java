package com.syj.demo.thread._2020.join3;

/**
 * @Description 线程A
 * @Date 2020/6/7 14:48
 * @Created by shiyongjun1
 */
public class ThreadA extends Thread {

    private ThreadB b;

    public ThreadA(ThreadB b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            synchronized (b) {
                System.out.println("begin a, name = " + Thread.currentThread().getName() + ", time = " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("begin a, name = " + Thread.currentThread().getName() + ", time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
