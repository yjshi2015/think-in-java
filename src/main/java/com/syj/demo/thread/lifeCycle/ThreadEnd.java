package com.syj.demo.thread.lifeCycle;

/**
 * 如何优雅的结束线程
 */
public class ThreadEnd {

    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        while (true) {
            if (t.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // 线程t在被interrupt后抛出interruptException异常,并且中断标识被清除
                // 所以要重置中断标识,否则系统会陷入死循环
                t.interrupt();
                e.printStackTrace();
            }
        }
    }
}
