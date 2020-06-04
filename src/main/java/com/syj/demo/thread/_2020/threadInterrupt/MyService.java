package com.syj.demo.thread._2020.threadInterrupt;

/**
 * Created by Administrator on 2020/6/4.
 */
public class MyService extends Thread {

    @Override
    public void run() {
        for (int i=0; i<500000; i++) {
            System.out.println(" i = " + (i+1));
        }
    }
}
