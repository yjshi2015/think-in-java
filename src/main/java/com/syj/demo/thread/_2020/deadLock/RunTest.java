package com.syj.demo.thread._2020.deadLock;

/**
 * Created by Administrator on 2020/5/31.
 *
 * 死锁：双方互相持有对方的锁的情况，只要互相等待对方释放锁，就有可能出现死锁。
 *
 * 死锁的检测：
 * 1.进入CMD工具，再进入JDK的安装文件夹中的bin目录，执行jps命令，得到当前执行的java线程；
 * 2.再执行jstack -l 线程id，查看结果，就可以定位到程序中有问题的代码行
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            DeadThread t1 = new DeadThread();
            t1.setUserName("a");

            Thread thread1 = new Thread(t1);
            thread1.start();
            Thread.sleep(100);

            t1.setUserName("b");
            Thread thread2 = new Thread(t1);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
