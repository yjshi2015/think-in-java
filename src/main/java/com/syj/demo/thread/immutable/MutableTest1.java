package com.syj.demo.thread.immutable;

/**
 * 多线程同时读写同一共享变量存在并发问题,这里必要条件之一是读写.
 * 如果只有读,没有写,是没有并发问题的.
 *
 * 解决并发问题,最简单的办法就是让共享变量只有读操作,没有写操作.
 *
 */
public class MutableTest1 {

    public static void main(String[] args) throws InterruptedException {

        // 不可变对象只有读操作,没有写操作,是不存在线程安全的
        final String obj = "zhangsan";

        for (int i=0; i<10; i++) {
            new Thread(() -> {
                String tem = obj.replace("zhang", "wang");
                System.out.println("tem:" + tem);
            }).start();
        }
    }
}
