package com.syj.demo.thread._2020.waitNotifyAll;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程启动入口
 * @Date 2020/6/6 20:21
 * @Created by shiyongjun1
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        WaitService waitService = new WaitService();
        Thread t1 = new Thread(() -> waitService.waitMethod(lock));
        t1.setName("t1");

        Thread t2 = new Thread(() -> waitService.waitMethod(lock));
        t2.setName("t2");

        Thread t3 = new Thread(() -> waitService.waitMethod(lock));
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();

        TimeUnit.SECONDS.sleep(3);

        // 唤醒所有等待线程，等待线程启动后，需要再次抢夺锁，然后串行进行！！！
        NotifyAllThread notifyAllThread = new NotifyAllThread(lock);
        notifyAllThread.start();
    }
}
