package com.syj.demo.thread._2020_kaitao.pool;

/**
 * Created by Administrator on 2020/6/26.
 */
public class JavaPoolSummary {



    /**
     *
     * 线程池的目的类似于连接池，通过减少频繁创建和销毁线程来降低性能损耗。每个线程都需要一个内存栈，用于
     * 存储如局部变量、操作栈等信息，可以通过-Xss参数来调整每个线程栈大小（64位操作系统默认1024KB，可以根据实际
     * 情况调小，比如256KB）,通过调整该参数可以创建更多地线程，不过JVM不能无限制的创建线程，通过使用线程池可以
     * 限制创建的线程数，从而保护系统。线程池一般配合队列一起工作，使用线程池限制“并发处理任务的数量”，然后设置
     * 队列的大小，当任务超过队列大小时，通过一定的拒绝策略来处理，这样可以保护系统免受大流量而导致崩溃--只是部分
     * 拒绝服务，还有一部分是可以正常服务的。
     *
     * 根据任务类型是IO密集型还是CPU密集型、CPU核数，来设置合理的线程池大小、队列大小、拒绝策略，并进行压测和
     * 不断调优来决定适合自己场景的参数
     *
     * maxPoolSize设置的过大会导致瞬间线程数非常多。
     *
     * 使用Executors.newFixedThreadPool时，因为没有设置队列大小，默认为Integer.MAX_VALUE，如果有大量任务被
     * 缓存到LinkedBlockingQueue中等待线程执行，会出现GC变慢等问题，造成系统响应慢设置OOM
     */
    public static void main(String[] args) {

    }
}
