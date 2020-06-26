package com.syj.demo.thread._2020_kaitao.pool;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2020/6/26.
 */
public class HowToCreateJavaPool {


    /**
     * java线程池，使用Executors来创建，提供了ExecutorService的3种实现
     * 1.ThreadPoolExecutor：标准线程池
     * 2.ScheduledThreadPoolExecutor:支持延迟任务的线程池
     * 3.ForkJoinPool:类似于ThreadPoolExecutor，但是使用work-stealing模式，其会为线程池中的每个线程创建
     *   一个队列，从而用work-stealing（任务窃取）算法使得线程可以从其他线程队列里窃取任务来执行。即如果自己
     *   的任务处理完成了，则可以去忙碌的工作线程哪里窃取任务执行。
     */
    public static void main(String[] args) {
        // 1.创建单线程的线程池，只有1个线程，使用无界队列
        ExecutorService poolSingle = Executors.newSingleThreadExecutor();
        // 等价于
        /*public static ExecutorService newSingleThreadExecutor() {
            return new Executors.FinalizableDelegatedExecutorService
                    (new ThreadPoolExecutor(1, 1,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>()));
        }*/

        // 2.创建固定数量的线程池,有N个线程，使用无界队列
        ExecutorService poolFixed = Executors.newFixedThreadPool(10);
        // 等价于
        /*public static ExecutorService newFixedThreadPool(int nThreads) {
            return new ThreadPoolExecutor(nThreads, nThreads,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
        }*/


        // 3.创建可缓存的线程池，初始大小为0，线程池最大大小为Integer.MAX_VALUE。其使用
        // SynchonousQueue线程同步队列，一个没有数据缓冲的阻塞队列。对其执行put操作后必须等待take
        // 操作消费该数据，反之亦然。该线程池不限制最大大小，如果线程池有空闲则线程复用，否则
        // 会创建一个新线程。如果线程池中的线程空闲60秒，则被回收。该线程默认最大大小为Integer.MAX_VALUE
        // 请确认必要后使用该线程池
        ExecutorService poolCache = Executors.newCachedThreadPool();
        // 等价于，切记，是个同步队列
        /*public static ExecutorService newCachedThreadPool() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());*/

        // 4.支持延迟执行的线程池，其使用DelayedWorkQueue实现任务延迟
        ScheduledExecutorService poolScheduled = Executors.newScheduledThreadPool(10);
        // 等价于,结合和Timer的定时执行功能
        /*retuen new ScheduledThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
                new DelayedWorkQueue())*/

        // 5 work-stealing线程池，默认并行数为Runtime.getRuntime().avaliableProcessors()，
        // 注意：对于doctor容器而言，该方法获取到的值为物理机的CPU个数，而非该容器的个数，需要通过JVM进行调整
        ExecutorService poolForkJoin = Executors.newWorkStealingPool(5);
        // 等价于
        /*return new ForkJoinPool
                (parallelism,
                 ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                 null, true)*/
    }

}
