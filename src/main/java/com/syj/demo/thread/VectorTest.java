package com.syj.demo.thread;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * 发生线程并发问题都概率较小，需要尝试很多次，才会发现vector的size>1
 */
public class VectorTest {

    public static void main(String[] args) throws InterruptedException {
        final Demo demo = new Demo();
        final Vector<String> vector = new Vector<>();
        final CountDownLatch latch = new CountDownLatch(1);

        for (int i=0; i<1000; i++) {
            new Thread((Runnable) () -> {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo.add(vector, "a");
            }, "threadname"+i).start();
        }

        latch.countDown();

        Thread.sleep(5000);

        System.out.println("============" + vector.size());
    }


    public static class Demo {

        /**
         * @desc 两个synchronized修饰的方法组合在一起并不能保证线程安全，
         * 假设2个线程同时进行了第一步contains的判断，通过后，都会执行
         * 第二步add的操作！！！
         * (contains方法虽然不是直接用synchronized修饰的，但是它调用的index0f方法是通过synchronized修饰的)
         * @param v
         * @param obj
         */
        public void add(Vector<String> v, String obj) {
            if (!v.contains(obj)) {
                v.add(obj);
            }
        }
    }
}
