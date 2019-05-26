package com.syj.demo.thread.threadPool;

import java.util.concurrent.Callable;

public class MyTask implements Callable<String> {

    private int id;

    MyTask(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        String result = null;
        if (id == 0) {
            result = getPriceByS1();
        } else if (id == 1) {
            result = getPriceByS2();
        } else if (id == 2) {
            result = getPriceByS3();
        }
        System.out.println("===========result:" + result);
        return result;
    }

    String getPriceByS1() {
        int temp = 1/0;
        return "R1";
    }
    String getPriceByS2() {
        return "R2";
    }
    String getPriceByS3() {
        return "R3";
    }

}
