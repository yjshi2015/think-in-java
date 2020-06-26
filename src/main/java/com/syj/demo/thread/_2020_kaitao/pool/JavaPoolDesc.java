package com.syj.demo.thread._2020_kaitao.pool;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2020/6/26.
 */
public class JavaPoolDesc {


    public static void main(String[] args) {
        // 核心线程数
        int corePoolSize = 5;

        // 最大线程数
        int maxPoolSize = 50;

        // 空闲线程存活时间
        int keepAliveTime = 60;

        // 线程池使用的任务缓存队列，包括有界阻塞数组队列ArrayBlockQueue和有界/无界阻塞链表队列
        // LinkedBlockingQueue、优先级阻塞队列PriorityBlockingQueue、无缓冲区同步阻塞队列
        // SynchronouseQueue。有界队列必须设置合理的队列大小
        LinkedBlockingQueue workQueue = new LinkedBlockingQueue(10000);

        // 创建线程的工厂，可以设置线程的名字、是否后台线程
        ThreadFactory myThreadFactor = null;

        // 当缓冲队列满之后的拒绝策略,包括
        // Abort: 直接抛出RejectedExectionException)
        // Discard: 按照FIFO丢弃
        // DiscardOldest: 按照LRU丢弃
        // CallsRun: 主线程执行
        // 自定义拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.SECONDS,
                workQueue, rejectedExecutionHandler);

        // 线程池不在使用后记得停止掉，可以调用shutdown来确保不再接受新的任务，并且将线程池中已有任务执行
        // 完成后再退出
        pool.shutdown();

    }
}
