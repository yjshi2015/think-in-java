package com.syj.demo.thread._2020.join;

import java.util.concurrent.TimeUnit;

/**
 * @Description 业务线程
 * @Date 2020/6/7 14:07
 * @Created by shiyongjun1
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        try {
            int secondValue = (int) (Math.random() * 10000);
            System.out.println("secondValue = " + secondValue);
            TimeUnit.MILLISECONDS.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
