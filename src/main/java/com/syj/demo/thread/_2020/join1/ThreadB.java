package com.syj.demo.thread._2020.join1;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程B
 * @Date 2020/6/7 14:27
 * @Created by shiyongjun1
 */
public class ThreadB extends Thread {

    @Override
    public void run() {
        try {
            System.out.println(" b run begin timer=" + System.currentTimeMillis());
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.println(" b run   end timer=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void bService() {
        System.out.println("打印了bService timer=" + System.currentTimeMillis());
    }
}
