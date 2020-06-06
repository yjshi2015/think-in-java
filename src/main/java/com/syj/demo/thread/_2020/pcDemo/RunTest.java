package com.syj.demo.thread._2020.pcDemo;

/**
 * @Description 线程启动入口
 * @Date 2020/6/6 22:32
 * @Created by shiyongjun1
 *
 * 使用生产、消费模式，需要注意2点：
 * 1.不能使用notify()，必须使用notifyAll()，避免只唤醒了同类（比如消费者），造成所有线程都在wait，从而假死
 * 2.不能使用if条件作为判断线程的执行条件，因为线程被唤醒后，执行条件有可能发生变化
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
