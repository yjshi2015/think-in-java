package com.syj.demo.thread._2020.threadInterrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程启动入口
 * @Date 2020/6/6 14:14
 * @Created by shiyongjun1
 */
public class RunTest3 {

    public static void main(String[] args) {
        try {
            MyService3 thread = new MyService3();
            thread.start();

            TimeUnit.SECONDS.sleep(2);
            // 建议使用业务逻辑中“抛异常”的方式来实现线程的停止，因为catch模块中可以对异常的信息进行相关的处理，
            // 而且异常流能更好的的控制程序运行流程，虽然可以通过return来实现线程的中断，但太多return容易对代
            // 码造成污染
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main exception");
            e.printStackTrace();
        }

    }
}
