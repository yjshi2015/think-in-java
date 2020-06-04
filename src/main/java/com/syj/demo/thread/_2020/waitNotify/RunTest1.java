package com.syj.demo.thread._2020.waitNotify;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/3.
 */
public class RunTest1 {

    public static void main(String[] args) {
        try {
            Object lock = new Object();
            Thread waitThread = new Thread(new WaitThread1(lock));
            waitThread.start();

            TimeUnit.MILLISECONDS.sleep(500);
            Thread notifyThead = new Thread(new NotifyThread1(lock));
            notifyThead.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
