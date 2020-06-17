package com.syj.demo.thread._2020.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description 测试入口
 * @Date 2020/6/15 8:14
 * @Created by shiyongjun1
 */
public class RunTest {

    static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    static RpcService rpcService = new RpcService();
    static HttpService httpService = new HttpService();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            doSomething(i + "");
        }
    }


    public static void doSomething(String param) {
        try {
            System.out.println("执行并行任务, time=" + System.currentTimeMillis());
            Future<String> rpcFuture = threadPool.submit(() -> rpcService.getRpcResult(param));
            Future<String> httpFuture = threadPool.submit(() -> httpService.getHttpResutl(param));
            String rpcResult = rpcFuture.get();
            System.out.println(" rpcResult:" + rpcResult + ",time=" + System.currentTimeMillis());
            String httpResult = httpFuture.get();
            System.out.println("httpResult:" + httpResult + ",time=" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class RpcService {
        String getRpcResult(String param) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return param;
        }
    }

    static class HttpService {
        String getHttpResutl(String param) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return param;
        }
    }
}
