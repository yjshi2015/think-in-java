package com.syj.demo.thread.threadPerMessage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * 线程池版的Thread-Per-Message
 *
 *
 */
public class Echo2 {

    public void init() {
        // 创建最大线程数为500的线程池
//        ExecutorService es = Executors.newFixedThreadPool(500);
        ExecutorService es = new ThreadPoolExecutor(
                50, 500,
                60L, TimeUnit.SECONDS,
                // 注意创建有界队列
                new LinkedBlockingDeque<Runnable>(2000),
                // 根据业务需要实现ThreadFactory
                r -> {
                    return new Thread(r, "echo-"+r.hashCode());
                },
                // 根据业务需要实现拒绝策略
                new ThreadPoolExecutor.CallerRunsPolicy()
        );


        // 处理请求
        try {
            final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 将请求处理任务交给线程池
                es.execute(() -> {
                    try {
                        // 读socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        sc.read(rb);
                        // 模拟处理请求
                        TimeUnit.SECONDS.sleep(3);
                        // 写socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        // 关闭socket
                        sc.close();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//            ssc.close();
            es.shutdown();
        }
    }
}
