package com.syj.demo.mybatis.chapter06.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2020/10/13.
 * JDK动态代理
 * ①编写接口和服务类，这是真正的服务提供者，在JDK代理中是必须的
 * ②编写代理类，提供绑定 和 代理方法
 */
public class HelloServiceProxy implements InvocationHandler {


    /**
     * 真实服务对象
     */
    private Object target;

    /**
     * 绑定委托对象，并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 通过代理对象调用方法，首先进入这个方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("######################这是JDK代理#######################");
        Object result = null;
        // 反射方法调用前
        System.err.println("准备说hello");
        // 执行方法
        result = method.invoke(target, args);
        // 反射方法调用后;
        System.err.println("说过hello了");
        return result;
    }
}
