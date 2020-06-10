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
            System.out.println("线程是否停止1？ = " + thread.interrupted());
            System.out.println("线程是否停止2？ = " + thread.interrupted());

            // interrupt()方法 和 interrupted()方法配合使用时，第一个方法是给线程设置中断的标记，第二个方法通过
            // true、false来识别是否被设置了中断的标记，并且同时会清楚该标记，所以清除中断标记后，第二次调用
            // interrupted()方法后，发现未被中断，故返回false
            Thread.currentThread().interrupt();
            // 识别出有中断标记，返回true，并且清除中断标记
            System.out.println("main线程是否停止1？ = " + Thread.interrupted());
            // 发现线程没有中断标记，所以返回false
            System.out.println("main线程是否停止2？ = " + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main  end");
    }
}
