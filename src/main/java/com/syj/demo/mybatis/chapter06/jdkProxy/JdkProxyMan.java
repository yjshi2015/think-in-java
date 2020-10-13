package com.syj.demo.mybatis.chapter06.jdkProxy;

/**
 * Created by Administrator on 2020/10/13.
 */
public class JdkProxyMan {

    public static void main(String[] args) {
        HelloServiceProxy helloServiceHandler = new HelloServiceProxy();

        HelloService proxy = (HelloService) helloServiceHandler.bind(new HelloServiceImpl());

        proxy.sayHello("syj");
    }
}
