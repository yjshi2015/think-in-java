package com.syj.demo.mybatis.chapter06.cglibProxy;

/**
 * Created by Administrator on 2020/10/13.
 */
public class HelloServiceNoImpl {

    public void sayHello(String name) {
        System.err.println("hello " + name);
    }
}
