package com.syj.demo.thread._2020.reentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/10.
 *
 * RennetrantLock初识：
 * 在Java多线程中，可以使用synchronized关键字来实现线程之间的同步互斥，但在JDK1.5中新增加了ReentrantLock类也能达到
 * 同样的效果，并且在扩展功能上也更加强大，比如具有嗅探定位、多路分支通知等功能，使用上也比synchronized更加灵活
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        Thread t1 = new Thread(() -> myService.methodA());
        Thread t2 = new Thread(() -> myService.methodA());
        Thread t3 = new Thread(() -> myService.methodB());
        Thread t4 = new Thread(() -> myService.methodB());
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t4.setName("t4");

        t1.start();
        t2.start();

        TimeUnit.MILLISECONDS.sleep(100);
        t3.start();
        t4.start();
    }
}
