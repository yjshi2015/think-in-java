package com.syj.demo.thread.immutable;

import java.util.concurrent.TimeUnit;

public class MutableClass {

    private int value = 10;

    public void set(int newVal) {
        this.value = newVal;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int get() {
        return this.value;
    }
}
