package com.syj.demo.thread._2020.threadInterrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/4.
 * 如何判读线程的状态是不是停止的，Thread类里提供了两种方法：
 * 1.this.interrupted():测试当前线程是否已经中断，当前线程指的是运行this.interrupted()方法的线程，并且该方法可以清楚中断状态！
 * 2.this.isinterrupted():测试线程是否已经中断
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            MyService thread = new MyService();
            thread.start();

            TimeUnit.SECONDS.sleep(3);
            // MyService线程中断
            thread.interrupt();
            // 判断当前线程是否已经中断，这个“当前线程”是main，它从未中断过，所以打印的是false
            System.out.println("是否停止1？ = " + thread.interrupted());
            System.out.println("是否停止2？ = " + thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main  end");
    }
}
