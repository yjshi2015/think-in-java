package com.syj.demo.thread.threadPoolBatch;

import org.springframework.scheduling.config.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程池中的每个线程默认是处理一条任务,但有些场景我们可以使用每个线程"批量"执行任务,后者的效率更高.
 *
 * 因为Java的线程和操作系统的线程是一对一的,也就是说线程的创建成本是很高的.如果我们能用更少的线程,
 * 去做更多的事情(即批量执行),岂不是更好
 *
 * 如果让每个线程去批量执行任务,那就是我们所熟知的生产--消费模型了,这种模型中,我们只需增加一个"阻塞
 * 队列"即可.
 *
 */
public class ThreadPoolBatch {

    // 任务队列
    BlockingQueue<Task> bq = new LinkedBlockingQueue<>(2000);
    // 启动5个消费者线程,执行批量任务
    void start() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i=0; i<5; i++) {
            es.execute(() -> {
                try {
                    while (true) {
                        // 获取批量任务
                        List<Task> ts = poolTasks();
                        // 执行批量任务
                        execTasks(ts);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    // 从任务队列中获取批量任务
    List<Task> poolTasks() throws InterruptedException{
        List<Task> ts = new LinkedList<>();
        // 阻塞式获取一条任务
        Task t = bq.take();
        while (t != null) {
            ts.add(t);
            // 非阻塞时获取一条任务
            t = bq.poll();
        }
        return ts;
    }

    // 批量执行任务
    void execTasks(List<Task> ts) {
        // 省略业务代码无数
    }
}
