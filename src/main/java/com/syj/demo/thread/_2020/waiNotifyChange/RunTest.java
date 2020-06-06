package com.syj.demo.thread._2020.waiNotifyChange;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程启动入口
 * @Date 2020/6/6 22:09
 * @Created by shiyongjun1
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);

        Thread t1 = new Thread(() -> subtract.substract());
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(() -> subtract.substract());
        t2.setName("t2");
        t2.start();
        // 线程t3唤醒所有wait线程后，那么第一个实现减操作的线程能正确的删除list中索引为0的元素，
        // 但第二个实现减操作的线程则会出现数组下标越界，因为list中只增加了一个元素，所以也只能
        // 删除一个元素
        TimeUnit.MILLISECONDS.sleep(1000);
        Thread t3 = new Thread(() -> add.add());
        t3.setName("t3");
        t3.start();
    }

}
