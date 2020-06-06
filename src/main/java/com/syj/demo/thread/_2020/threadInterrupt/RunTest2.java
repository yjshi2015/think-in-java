package com.syj.demo.thread._2020.threadInterrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Description 线程启动入口
 * @Date 2020/6/6 14:06
 * @Created by shiyongjun1
 */
public class RunTest2 {

    public static void main(String[] args) {
        try {
            MyService2 thread = new MyService2();
            thread.start();

            TimeUnit.SECONDS.sleep(2);
            // 线程被中断，虽然业务逻辑中判断了中断标记，但目前方式，业务逻辑依然会继续执行
            // 并没有真正的中断线程，推荐使用异常法来中断线程，看RunTest3
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main exception");
            e.printStackTrace();
        }
        System.out.println("main end!");

    }
}
