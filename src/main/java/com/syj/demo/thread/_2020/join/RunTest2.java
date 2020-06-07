package com.syj.demo.thread._2020.join;

/**
 * @Description 线程启动入口
 * @Date 2020/6/7 14:19
 * @Created by shiyongjun1
 */
public class RunTest2 {

    public static void main(String[] args) {
        try {
            MyThread1 t1 = new MyThread1();
            t1.start();

            // 主线程等待t1线程2s后，再开始执行
            // join()方法底层使用的是wait()机制，保证当前线程x出于wait状态，从而使线程z也得不到执行
            t1.join(2000);
            System.out.println("  end timer = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
