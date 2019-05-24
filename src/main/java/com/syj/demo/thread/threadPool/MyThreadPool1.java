package com.syj.demo.thread.threadPool;

import java.util.concurrent.*;

/**
 * 说明线程池的注意事项
 */
public class MyThreadPool1 {

    /**
     * 线程最小数量,即线程池一定会保留该数量的线程
     */
    int corePoolSize;
    /**
     * 线程池最大数,即使任务队列再大,线程的最大数也是如此
     */
    int maxPoolSize;
    /**
     * 当(keepAliveTime & unit)时间内,没有可执行的任务,且线程数量大于corePoolSize,则空闲的线程就要被回收,最终
     * 线程的数量恢复到corePoolSize
     */
    long keepAliveTime;
    TimeUnit unit;
    /**
     * 任务队列
     */
    BlockingQueue<Runnable> workQueue;
    /**
     * 可以自定义如何创建线程,比如给线程指定一个有意义的名字
     */
    ThreadFactory threadFactory;
    /**
     * 定义任务的拒绝策略.如果所有的线程都很忙,且任务队列也满了(前提是有界队列),那么此时提交任务,线程池就会执行该拒绝策略
     * 共4种:
     * 1. CallerRunsPolicy:提交任务的线程自己去执行该任务
     * 2. AbortPolicy:默认策略,即 throws RejectedExectionException
     * 3. DiscardPolicy: 丢弃任务,且不抛异常
     * 4. DiscardOldestPolicy: 丢弃最老的任务,即把最早进入队列的抛弃,然后把新任务添加到队列中
     */
    RejectedExecutionHandler handler;


    /**
     * 推荐的线程池创建方式
     */
    ThreadPoolExecutor pool = new ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            keepAliveTime,
            unit,
            workQueue,
            threadFactory,
            handler
    );

    /**
     * 使用Executors来创建线程池,比上面的例子更方便
     */
    ExecutorService pool1 = Executors.newFixedThreadPool(1);
}
