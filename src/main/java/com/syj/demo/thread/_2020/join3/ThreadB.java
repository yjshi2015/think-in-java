package com.syj.demo.thread._2020.join3;

/**
 * @Description 线程B
 * @Date 2020/6/7 14:46
 * @Created by shiyongjun1
 */
public class ThreadB extends Thread {

    /**
     * 注意：此处把run方法设置为线程同步的
     */
    @Override
    synchronized public void run() {
        try {
            System.out.println("begin b, name = " + Thread.currentThread().getName() + ", time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("  end b, name = " + Thread.currentThread().getName() + ", time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
