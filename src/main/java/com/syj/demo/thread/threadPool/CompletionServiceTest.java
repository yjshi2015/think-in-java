package com.syj.demo.thread.threadPool;

import java.util.concurrent.*;

/**
 * MyThreadPool2线程池有一个瑕疵,如果在获取队列result的第一个元素结果时,耗时很长,那么即便获取第二个,第三个元素时间很短,
 * 线程也会被阻塞,无法进行.
 *
 * 当执行批量任务时,有没有方式让"先"完成的任务,接着往下走呢?当然是有的,就是"CompletionService"
 *
 * CompletionService内部维护了一个阻塞队列,然后在"主线程"中消费阻塞队列,这样就能保证先进队列的元素,先被消费!
 */
public class CompletionServiceTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //创建completionService,构造函数如果不指定BlockingQueue<Future<V>>参数,
        // 就会默认使用无界的LinkedBlockingQueue队列
        CompletionService<String> cs = new ExecutorCompletionService<>(pool);
        //异步向电商S1询价
        MyTask task1 = new MyTask(0);
        cs.submit(task1);
        //异步向电商S2询价
        MyTask task2 = new MyTask(1);
        cs.submit(task2);
        //异步向电商S3询价
        MyTask task3 = new MyTask(2);
        cs.submit(task3);

        //将询价结果异步保存
        for (int i=0; i<3; i++) {
            //从队列中获取线程池的最先完成的执行结果
            String r = cs.take().get();
            System.out.println("=========结果:"+ r);
        }

        pool.shutdown();
    }



}
