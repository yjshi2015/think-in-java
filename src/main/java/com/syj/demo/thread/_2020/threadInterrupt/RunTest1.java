package com.syj.demo.thread._2020.threadInterrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程中断
 * @Date 2020/6/6 13:43
 * @Created by shiyongjun1
 */
public class RunTest1 {

    public static void main(String[] args) throws InterruptedException {
        MyService thread = new MyService();
        thread.start();

        TimeUnit.SECONDS.sleep(3);
        thread.interrupt();
        // isInterrupted()方法判断是否已经中断状态，但不清除中断标志
        System.out.println("是否停止1 ? =" + thread.isInterrupted());
        System.out.println("是否停止2 ? =" + thread.isInterrupted());
        System.out.println(" end");
    }
}
