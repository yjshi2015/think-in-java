package com.syj.demo.thread.lock;

/**
 * 这种实现方式的优势:
 * 1.不用在getInstance方法上加同步锁,减少了性能的影响
 * 2.双重判断来保证最终只有一个线程来执行初始化
 * 3.volatile保证变量对所有线程是可见的
 */
public class Singleton {

    private static volatile Singleton singleton;
    // 私有化构造方法
    private Singleton() {}
    // 获取实例(单例)
    private static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
