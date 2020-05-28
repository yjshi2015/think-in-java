package com.syj.demo.thread.interrupt;

/**
 * Created by Administrator on 2020/5/28.
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i=0; i<100000; i++) {
            if (this.interrupted()) {
                System.out.println("已经是停止状态！线程要退出了");
                break;
            }
            System.out.println("i=" + (i+1));
        }
    }
}
