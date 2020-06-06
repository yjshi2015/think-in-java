package com.syj.demo.thread._2020.threadInterrupt;

/**
 * @Description 业务类
 * @Date 2020/6/6 14:03
 * @Created by shiyongjun1
 */
public class MyService2 extends Thread {

    @Override
    public void run() {
        for (int i=0; i<500000; i++) {
            if (this.interrupted()) {
                System.out.println("线程已经是停止状态，要退出了");
                break;
            }
            System.out.println("i = " + (i+1));
        }

        System.out.println("我被输出了，如果下面有业务逻辑，会接着执行");
    }
}
