package com.syj.demo.thread._2020.volatileTest;

/**
 * Created by Administrator on 2020/6/2.
 */
public class RunThread extends Thread {

    // 有问题的代码
//    private boolean isRunning = true;
    // 解决线程的可见性
    volatile private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入了 run 方法");
        // 使用volatile修饰，保证每次从主内存读取最新的值，保证工作内存跟主内存数据一致
        while (isRunning){}
        System.out.println("线程被终止");
    }
}
