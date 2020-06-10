package com.syj.demo.thread._2020.threadInterrupt;

/**
 * @Description 业务逻辑类
 * @Date 2020/6/6 14:11
 * @Created by shiyongjun1
 */
public class MyService3 extends Thread {

    @Override
    public void run() {
        try {
            for (int i=0; i<500000; i++) {
                if (this.interrupted()) {
                    System.out.println("线程被中断了");
                    throw new InterruptedException("线程中断啦，通过抛异常来结束业务逻辑");
                }
                System.out.println("i = " + (i+1));
            }
        } catch (InterruptedException e) {
            System.out.println("进入业务逻辑的异常捕获里，可以单独处理中断后的逻辑");
            e.printStackTrace();
        }

    }
}
