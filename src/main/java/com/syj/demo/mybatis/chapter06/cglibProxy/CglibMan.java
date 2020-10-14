package com.syj.demo.mybatis.chapter06.cglibProxy;


/**
 * Created by Administrator on 2020/10/14.
 */
public class CglibMan {

    public static void main(String[] args) {
        HelloServiceCglib handler = new HelloServiceCglib();

        HelloServiceNoImpl proxy = (HelloServiceNoImpl) handler.getInstance(new HelloServiceNoImpl());

        proxy.sayHello("syj");
    }
}
