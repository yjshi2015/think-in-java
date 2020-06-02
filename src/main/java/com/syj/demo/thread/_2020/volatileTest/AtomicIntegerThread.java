package com.syj.demo.thread._2020.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2020/6/2.
 */
public class AtomicIntegerThread extends Thread{

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i=0; i<10000; i++) {
            System.out.println("count = " + count.incrementAndGet());
        }
    }
}
