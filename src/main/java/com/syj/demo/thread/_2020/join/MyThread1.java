package com.syj.demo.thread._2020.join;

/**
 * @Description 业务线程类
 * @Date 2020/6/7 14:18
 * @Created by shiyongjun1
 */
public class MyThread1 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("begin timer = " + System.currentTimeMillis());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
