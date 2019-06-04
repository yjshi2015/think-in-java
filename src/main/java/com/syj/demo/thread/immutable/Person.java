package com.syj.demo.thread.immutable;

public class Person implements Runnable{

    public Long num;

    @Override
    public void run() {
        for (int i=0; i<1000; i++) {
            num++;
        }
    }
}
