package com.syj.demo.thread.threadPool;

import java.util.concurrent.Callable;

public class MyTask implements Callable<String> {

    private int id;

    MyTask(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        if (id == 0) {
            return getPriceByS1();
        } else if (id == 1) {
            return getPriceByS2();
        } else if (id == 2) {
            return getPriceByS3();
        }
        return null;
    }

    String getPriceByS1() {
        return "R1";
    }
    String getPriceByS2() {
        return "R2";
    }
    String getPriceByS3() {
        return "R3";
    }

}
