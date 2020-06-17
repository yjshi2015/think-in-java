package com.syj.demo.thread._2020_kaitao.async;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2020/6/17.
 *
 * 异步callback，即首先发出网络请求，当服务端返回时回调相关方法。
 *
 * HTTPAsyncClient使用基于NIO的异步I/O模型实现，它实现了Reactor模式，摒弃阻塞I/O模型one thread per connection,
 * 采用线程时分发时间通知，从而有效支撑大量并发连接。
 *
 * 注意！这种异步回调机制，并不能提升性能，而是为了支撑大量的并发连接 或者 提升吞吐量
 */
public class CallbackDemo {


    public static HttpAsyncClient httpAsyncClient;

    public static void initHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000)
                .setSocketTimeout(50000)
                .setConnectionRequestTimeout(1000)
                .build();

        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
                setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        //设置连接池大小
        ConnectingIOReactor ioReactor=null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(100);


        httpAsyncClient = HttpAsyncClients.custom().
                setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public static CompletableFuture<String> getHttpData(String url) {

        CompletableFuture asyncFuture = new CompletableFuture();

        HttpAsyncRequestProducer requestProducer = HttpAsyncMethods.create(new HttpPost(url));

        BasicAsyncResponseConsumer responseConsumer = new BasicAsyncResponseConsumer();

        FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                asyncFuture.complete(httpResponse);
            }

            @Override
            public void failed(Exception e) {
                asyncFuture.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                asyncFuture.cancel(true);
            }
        };

        httpAsyncClient.execute(requestProducer, responseConsumer, callback);
        return asyncFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        initHttpClient();

        CompletableFuture<String> future = CallbackDemo.getHttpData("http://www.jd.com");
        System.out.println("future : " + future.get());
    }
}
