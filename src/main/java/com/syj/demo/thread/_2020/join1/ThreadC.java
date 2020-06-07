package com.syj.demo.thread._2020.join1;

/**
 * @Description 线程C
 * @Date 2020/6/7 14:32
 * @Created by shiyongjun1
 */
public class ThreadC extends Thread {

    private ThreadB b;
    public ThreadC(ThreadB b) {
        this.b = b;
    }

    @Override
    public void run() {
        b.bService();
    }
}
