package com.syj.demo.thread._2020.waitNotifyAll;

import java.util.concurrent.TimeUnit;

/**
 * @Description 等待服务
 * @Date 2020/6/6 20:17
 * @Created by shiyongjun1
 */
public class WaitService {

    public void waitMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait ThreadName=" + Thread.currentThread().getName());
                lock.wait();
                System.out.println("wait 线程被唤醒，继续执行, ThreadName=" + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("end wait ThreadName=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
