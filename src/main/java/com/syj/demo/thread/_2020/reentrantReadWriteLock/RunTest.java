package com.syj.demo.thread._2020.reentrantReadWriteLock;

/**
 * @Description 测试入口
 * @Date 2020/6/14 10:35
 * @Created by shiyongjun1
 * <p>
 * ReentrantReadWriteLock读写锁，有2个锁，一个是跟读操作相关的锁，称为共享锁，另一个是跟写操作相关的锁，称为排它锁。
 * 也就是多个读锁之间不互斥，读锁与写锁互斥，写锁与写锁互斥。
 * <p>
 * 在没有线程进行写操作时，进行读操作的多个线程都可以获取读锁，而进行写操作的线程只有在获取写锁后才能进行写入操作。
 * <p>
 * 凡是涉及到写操作的，必须获取锁才能继续往下执行
 */
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        // 场景一：读写锁互斥
//        Thread readThread = new Thread(() -> myService.read());
//        readThread.setName("读线程");
//        readThread.start();
//        Thread.sleep(1000);
//        Thread writeThread = new Thread(() -> myService.write());
//        writeThread.setName("写线程");
//        writeThread.start();

        // 场景二：读锁共享
//        Thread readThread1 = new Thread(() -> myService.read());
//        readThread1.setName("读线程1");
//        readThread1.start();
//        Thread readThread2 = new Thread(() -> myService.read());
//        readThread2.setName("读线程2");
//        readThread2.start();

        // 场景三：写写锁互斥
        Thread writeThread1 = new Thread(() -> myService.write());
        writeThread1.setName("写线程1");
        writeThread1.start();
        Thread writeThread2 = new Thread(() -> myService.write());
        writeThread2.setName("写线程2");
        writeThread2.start();
    }
}
