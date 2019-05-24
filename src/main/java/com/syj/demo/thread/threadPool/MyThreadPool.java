package com.syj.demo.thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池伪代码,用来说明线程池原理
 */
public class MyThreadPool {
    /**
     * 利用阻塞队列实现生产-消费者模式
     * 注意:BlockingQueue是并发包concurrent下的线程安全类,并且线程池初始化时的队列大小类比于该workQueue
     */
    BlockingQueue<Runnable> workQueue;

    /**
     * 保存内部工作线程
     * 注意:我们使用线程池时指定线程的核心大小,最大数,类比于该list
     */
    List<WorkThread> threads = new ArrayList<>();

    /**
     * 线程池的初始化
     * @param poolSize
     * @param workQueue
     */
    MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        for (int i=0; i<poolSize; i++) {
            //创建工作线程
            WorkThread work = new WorkThread();
            work.run();
            threads.add(work);
        }
    }

    //提交任务
    void execute(Runnable command) {
        workQueue.add(command);
    }

    //工作线程负责消费任务,病执行任务
    class WorkThread extends Thread {

        public void run() {
            //循环取任务并执行
            while (true) {
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }

    // ====================使用实例========================
    public void test() {
        /**
         * 先创建有界阻塞队列
         */
        BlockingQueue<Runnable> tasksQueue = new LinkedBlockingDeque<>(200);
        /**
         * 创建线程池
         */
        MyThreadPool pool = new MyThreadPool(10, workQueue);
        /**
         * 提交任务
         */
        pool.execute(() -> {
            //todo 业务逻辑
            System.out.println("此处做了好多业务处理");
        });
    }



}
