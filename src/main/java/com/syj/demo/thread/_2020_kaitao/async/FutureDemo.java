package com.syj.demo.thread._2020_kaitao.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/17.
 *
 * 线程池配合Future实现，可以并行发出N个请求，然后等待最慢的一个返回，总响应时间为最慢的一个请求返回的时间。
 *
 * 但是这种方式阻塞了主线程，高并发时依然会造成线程数过多，频繁的CPU上下文切换
 *
 * 可以通过异步回调实现，请求发过来即结束，等服务端处理完后，再回调（这种方式并不能提升性能，只是为了支持大量
 * 并发连接或者提升了吞吐量）
 */
public class FutureDemo {

    final static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        System.out.println("time=" + System.currentTimeMillis() + " begin");
        RpcService rpcService = new RpcService();
        HttpService httpService = new HttpService();
        Future<String> rpcResultFuture = pool.submit(() -> rpcService.getRpcResutl());
        Future<String> httpResultFuture = pool.submit(() -> httpService.getHttpResult());
        try {
            String rpcResult = rpcResultFuture.get();
            System.out.println("time=" + System.currentTimeMillis() + ", rpcResult : " + rpcResult);
            String httpResult = httpResultFuture.get();
            System.out.println("time=" + System.currentTimeMillis() + ", httpResult : " + httpResult);
        } catch (Exception e) {
            if (rpcResultFuture != null) {
                rpcResultFuture.cancel(true);
            }
            if (httpResultFuture != null) {
                httpResultFuture.cancel(true);
            }
            System.out.println("线程执行异常" + e);
        }

    }

    static class RpcService {
        String getRpcResutl() throws Exception {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("time=" + System.currentTimeMillis() + ", RPC业务逻辑执行完毕");
            return "Rpc success";
        }
    }

    static class HttpService {
        String getHttpResult() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("time=" + System.currentTimeMillis() + ", Http业务逻辑执行完毕");
            return "http success";
        }
    }
}
