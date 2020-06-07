package com.syj.demo.thread._2020.join1;

/**
 * @Description 线程A
 * @Date 2020/6/7 14:29
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
                b.start();
                // 释放掉锁
                b.join();
                for (int i=0; i<Integer.MAX_VALUE; i++) {
                    String newString = new String();
                    Math.random();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
