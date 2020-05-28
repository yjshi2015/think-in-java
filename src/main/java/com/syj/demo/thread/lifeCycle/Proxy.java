package com.syj.demo.thread.lifeCycle;

/**
 * 监控采集系统,监控代理收到指令后,从监控目标搜集数据,然后回传给监控系统.处于性能考虑,
 * 有些监控项对系统性能影响很大,所以不能一直持续监控,需要终止操作
 *
 * 使用启动/结束采集操作来说明如何优雅的结束线程
 *
 * started变量的读写都在同步方法start()中,故不存在线程安全问题
 *
 * 但terminated为什么要采用volatile修饰?
 * start() stop()方法对于terminated的访问有余synchronize关键字,线程安全,但是start()
 * 方法中新起了一个线程rptthread,导致stop方法中对于terminated存在可见性问题,因此需要
 * volatile修饰
 */
public class Proxy {
    // 线程终止标识位
    volatile  boolean terminated = false;
    boolean started = false;
    // 采集线程
    Thread rptThread;
    // 启动采集功能
    synchronized void start() {
        // 不允许同时启动多个采集线程
        if (started) {
            return;
        }
        started = true;
        terminated = false;
        rptThread = new Thread(() -> {
            while (!terminated) {
                // 省略采集/回传实现
//                report()
                // 每隔2秒钟采集 回传一次数据
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // 重新设置线程中断状态
                    Thread.currentThread().interrupt();
                }
            }
            // 执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }

    // 终止采集功能
    synchronized void stop() {
        // 设置中断标识
        terminated = true;
        rptThread.interrupt();
    }
}
