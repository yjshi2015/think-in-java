package com.syj.demo.thread._2020.volatileTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/2.
 *
 * 执行后发生死循环，RunThread线程永远不会退出，这是为什么呢？
 *
 * 在启动RunThead线程时，变量private boolean isRunning = true;存在于工作内存 和 主内存中，在JVM被设置为-server模式时为了
 * 执行效率，线程一直在工作内存中取得isRunning的值是true。而代码thread.setRunning(false)，虽然被执行，却是更新的主内存中的
 * 值为false，所以一直是死循环状态。
 *
 * 这个问题其实是工作内存 和 主内存中的值不同步造成的。解决方案就是使用volatile关键字了，它能保证当线程访问isRunning这个变量
 * 时，强制从主内存中拉取最新的值。
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            RunThread thread = new RunThread();
            thread.start();
            TimeUnit.SECONDS.sleep(1);
            thread.setRunning(false);
            System.out.println("已经被赋值为false");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
