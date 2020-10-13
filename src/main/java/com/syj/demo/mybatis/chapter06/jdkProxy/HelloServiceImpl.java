package com.syj.demo.mybatis.chapter06.jdkProxy;

/**
 * Created by Administrator on 2020/10/13.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        System.err.println("hello " + name);
    }
}
