package com.syj.demo.thread._2020_rpc.rpcDemo;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2020/6/28.
 */
public class RunTest {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                RpcProvider.exporter("localhost", 8080);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        RpcConsumer<EchoService> consumer = new RpcConsumer<>();
        EchoService proxyService = consumer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8080));
        System.out.println("result : " + proxyService.echo("Are You Ok ?"));
        System.out.println("result : " + proxyService.syjMethod("SYJ"));
    }
}
