package com.syj.demo.thread._2020.reentrantLockPC;

/**
 * Created by Administrator on 2020/6/11.
 */
public class RunTest {

    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                myService.produce();
            }
        });
        t1.setName("生产线程");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                myService.consumer();
            }
        });
        t2.setName("消费线程");
        t1.start();
        t2.start();
    }
}
