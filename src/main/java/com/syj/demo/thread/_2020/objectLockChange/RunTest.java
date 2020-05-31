package com.syj.demo.thread._2020.objectLockChange;

/**
 * Created by Administrator on 2020/5/31.
 * 锁对象的改变，如下的代码
 *
 * 如果20行正常执行，则t2抢夺的是“456”这个锁，跟t1的锁是不同的，线程异步执行
 *
 * 如果20行注释掉，则线程t1和t2，共同争夺锁对象“123”，即使t1把lock改变成“456”，但t1和t2争夺的仍是锁“123”，
 * 线程同步执行
 *
 * 锁对象的改变：只要对象不变，即使对象的属性变化了，线程还是同步的。
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        Thread t1 = new Thread(() -> myService.testMethod());
        t1.setName("A");

        Thread t2 = new Thread(() -> myService.testMethod());
        t2.setName("B");

        t1.start();
        //等待50毫秒
//        TimeUnit.MILLISECONDS.sleep(50);
        t2.start();
    }
}
