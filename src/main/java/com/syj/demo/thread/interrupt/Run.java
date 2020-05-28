package com.syj.demo.thread.interrupt;

/**
 * Created by Administrator on 2020/5/28.
 *
 * Thread类中提供了两种方法
 * this.interrupted():测试当前线程是否已经中断，并清除中断（由this.interrupt()打上的）标记
 * this.isInterrupted()：测试线程是否已经中断，不清除中断标记
 */
public class Run {

    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(100);
            System.out.println("线程恢复");
            // 子线程的中断，是并行的，所以会出现先执行“主线程结束”，再执行“已经是停止状态！线程要退出了"
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("线程异常" + e);
        }
        System.out.println("主线程结束");
    }
}
