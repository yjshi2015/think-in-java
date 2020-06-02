package com.syj.demo.thread._2020.volatileTest;

/**
 * Created by Administrator on 2020/6/2.
 */
public class RunThead1 extends Thread{

    // 此处使用了static静态变量，所以能保证100个线程共享同一个变量
    // 如果使用普通变量，则各个线程使用各自的变量，不能共享，也不会存在线程问题
//    volatile private int count;
    volatile private static int count;

    private void addCount() {
        for (int i=0; i<100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }
}
