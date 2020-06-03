package com.syj.demo.thread._2020.waitNotify;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/3.
 *
 * 多线程修炼秘籍：外修互斥，内修可见
 *
 * 等待、通知机制的实现：
 * 方法wait()的作用是使当前执行代码的线程进行等待，wait()方法是Object类的方法，该方法用来将当前线程置入“预执行队列”中，
 * 并且在“wait（）”所在的代码行处停止执行，直到接到通知或被中断为止。
 *
 * 在调用wait（）前，线程必须获得该对象的对象级别锁，即只能在同步方法或同步代码块中调用wait（）方法。在执行wait（）方法
 * 后，当前线程释放锁。在从wait（）返回前，线程与其他线程竞争重新获得锁。
 *
 * 如果调用wait（）时没有持有锁，则会抛出IllegalMonitorStateException，它是RuntimeException的一个子类，因此，不需要
 * try-catch语句捕获异常。
 *
 * 方法notify（）也需要在同步方法或同步代码块中调用，即在调用前，线程也必须获得该对象的对象级别锁。如果没有，同样会抛出
 * IllegailMonitorStateException异常。该方法用来通知那些可能等待该对象的对象级别锁的线程，如果有多个线程等待，则由线程
 * 规划器随机挑选出一个呈wait状态的线程，对其发出notify通知，并使它等待获取该对象的对象级别锁。
 *
 * 需要说明的是，在执行notify（）方法后，当前线程并不会马上释放该对象锁，呈wait状态的线程也并不能马上获取该对象锁，需要
 * 等到执行notify（）方法的线程执行完整个synchronize代码块后，当前线程才会释放锁，呈wait状态的所在线程才可以获取该对象锁。
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new WaitThread(lock));
        t1.start();

        TimeUnit.MILLISECONDS.sleep(500);

        Thread t2 = new Thread(new NotifyThread(lock));
        t2.start();

    }
}
