package com.syj.demo.thread.threadPerMessage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Thread-Per-Message模式:即为每个任务分配一个独立的线程
 *
 * 以下的demo在高并发场景下并不可行.原因在于Java中的线程是一个重量级对象,创建成本很高.一方面创建线程比较耗时,
 * 另一方面占用的内存也比较大.
 *
 * 优化点:引入线程池.在Java语言里,线程和操作系统线程是一一对应的,本质上是将Java线程的调度权委托给操作系统线程.
 * 而操作系统在这方面是很成熟的,所以这种做法的好处是稳定/可靠,但也继承了操作系统线程的缺点:创建成本高.所以线程池
 * 是一个很稳妥的方案
 *
 * 另一种方案:轻量级线程.创建的成本低,和创建一个普通对象差不多;并且创建的速度和内存占用相比操作系统至少有一个
 * 数量级的提升,所以给予轻量级线程实现Thread-Per-Message模式就没有问题了
 *
 * OpenJDK有个Loom项目,就是用来解决Java语音轻量级线程问题,在这个项目中,轻量级线程被叫做Fiber
 */
public class Echo {

    public void init() {
        // 处理请求
        try {
            final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(8080));

            while (true) {
                //接收请求
                SocketChannel sc = ssc.accept();
                new Thread(() -> {
                    try {
                        // 读socket
                        ByteBuffer rb = ByteBuffer.allocateDirect(1024);
                        sc.read(rb);
                        // 模拟处理请求
                        Thread.sleep(3000);
                        // 写socket
                        ByteBuffer wb = (ByteBuffer) rb.flip();
                        sc.write(wb);
                        // 关闭socket
                        sc.close();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            ssc.close();
        }
    }


}
