package com.syj.demo.thread._2020.join;

/**
 * @Description 线程启动入口
 * @Date 2020/6/7 14:09
 * @Created by shiyongjun1
 *
 * 很多情况下，主线程创建并启动了子线程，如果子线程中要进行大量的耗时运算，主线程
 * 往往将早于子线程结束之前结束。这是，如果主线程想等待子线程执行完成之后再结束，
 * 比如子线程处理一个数据，主线程要获取这个数据中的值，就要用到join（）方法了
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            MyThread t1 = new MyThread();
            t1.start();
            // 方法join()的作用是使所属的线程对象x能正常执行run()方法中的人物，而使当前线程z进行
            // 无限期的阻塞，等待线程x销毁后再继续执行线程z后面的代码
            // 方法join()具有使线程排队运行的作用，有些类似同步的运行效果。join与synchronized的
            // 区别是：join在内部使用wait()方法进行等待，而synchronized关键字使用的是“对象监视器”
            // 的原理作为同步
            t1.join();
            System.out.println("t1执行完后再打印，做到了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
