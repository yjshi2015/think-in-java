package com.syj.demo.thread._2020.pcDemo;

/**
 * @Description 线程启动入口
 * @Date 2020/6/6 22:32
 * @Created by shiyongjun1
 */
public class RunTest {

    public static void main(String[] args) {
        String lock = new String("");
        Producer p = new Producer(lock);
        Consumer c = new Consumer(lock);

        Thread t1 = new Thread(() -> {
            while (true) {
                p.setValue();
            }
        });
        t1.setName("生产线程t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                c.getValue();
            }
        });
        t2.setName("消费线程t2");

        t1.start();
        t2.start();
    }
}
