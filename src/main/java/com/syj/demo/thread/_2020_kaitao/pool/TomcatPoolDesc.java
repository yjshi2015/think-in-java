package com.syj.demo.thread._2020_kaitao.pool;

/**
 * Created by Administrator on 2020/6/26.
 */
public class TomcatPoolDesc {


    /**
     * 已tomcat8为例，tomcat线程池配置如下，配置方式一：
     * <Connector port="8080" protocol="HTTP/1.1"
     * 请求等待队列大小。当tomcat中没有空闲线程处理连接请求时，新来的连接请求被放入等待队列，默认为100，
     * 当超过acceptCount时，请求将被聚聚
     * acceptCout="100"
     *
     * tomcat能处理的最大并发连接数，当超过后还是会接收连接请求，并放入等待队列（acceptc控制），连接会
     * 等待，不能被处理。BIO默认是maxThreads数
     * maxConnections="200"
     *
     * 线程池最小线程数，指定线程池中可以维持的空闲线程数
     * minSpareThreads="10"
     *
     * 最大线程数，当空闲一段时间，会释放到只保留minSpraeThread个线程。
     * maxThreads="200"
     *
     * connectionTimeout="20000"
     * redirectPort="8443" />
     *
     *
     * 举例，假设maxThreads=100，maxConnections=50，acceptCount=50，假设并发请求为200，则有50个线程
     * 并发处理50个请求，50个请求进入等待队列，剩余的100个请求被拒绝，也就是Tomcat的最大并发线程数量是由
     * maxThreads 和 maxConnextions的最小值决定的
     */
    public static void main(String[] args) {

    }
}
