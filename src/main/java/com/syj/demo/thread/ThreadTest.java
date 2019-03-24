package com.syj.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2019/3/22.
 */
public class ThreadTest {

    public static void main(String[] args) {

        NotifyDemo demo = new NotifyDemo();
        demo.run();
//        run();

    }

    synchronized static void run() {
        System.out.println("=======run begin==========");
        while (true) {
            try {
                System.out.println("======begin 释放锁资源========");
                ThreadTest.class.wait();
                System.out.println("======end 释放锁资源========");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("=======run end==========");
    }

    static class NotifyDemo implements Runnable {

        List<String> queue = new LinkedList<>();
        private Object notFull = new Object();
        private Object notEmpty = new Object();

        synchronized void enQueue() {
            while (queue.size() > 2) {
                try {
                    notFull.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add("1");
            System.out.println("=======添加了1个元素===========");
            notEmpty.notifyAll();
        }

        synchronized void deQueue() {
            while (queue.size() == 0) {
                try {
                    notEmpty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.remove(0);
            System.out.println("=======去除了1个元素===========");
            notFull.notifyAll();
        }

        @Override
        public void run() {
            System.out.println("======begin notify========");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enQueue();
            deQueue();
            System.out.println("======end notify========");
        }
    }
}
