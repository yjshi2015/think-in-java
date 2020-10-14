package com.syj.demo.mybatis.chapter06.cglibProxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/10/14.
 */
public class HelloServiceCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    // 回调方法
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.err.println("这是 CGLIB 动态代理");
        // 反射方法调用前
        System.err.println("准备说hello");
        Object returnObj = methodProxy.invokeSuper(o, args);
        System.err.println("说过了hello");
        return returnObj;
    }
}
