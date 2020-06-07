package com.syj.demo.thread._2020.join1;

import java.util.concurrent.TimeUnit;

/**
 * @Description 启动入口
 * @Date 2020/6/7 14:34
 * @Created by shiyongjun1
 *
 * 线程的join方法会释放掉锁
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();

            TimeUnit.SECONDS.sleep(1);
            ThreadC c = new ThreadC(b);
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
