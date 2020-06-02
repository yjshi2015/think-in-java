package com.syj.demo.thread._2020.volatileTest;

/**
 * Created by Administrator on 2020/6/2.
 */
public class RunTest3 {

    public static void main(String[] args) {
        AtomicIntegerThread countService = new AtomicIntegerThread();
        Thread t1 = new Thread(countService);
        Thread t2 = new Thread(countService);
        Thread t3 = new Thread(countService);
        Thread t4 = new Thread(countService);
        Thread t5 = new Thread(countService);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
