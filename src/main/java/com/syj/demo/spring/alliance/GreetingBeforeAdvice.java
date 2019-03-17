package com.syj.demo.spring.alliance;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/17.
 * @desc 方法执行前增强
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice{

    //在目标类方法执行前执行
    @Override
    public void before(Method method, Object[] args, Object obj) throws Throwable {
        String customerName = (String) args[0];
        System.out.println("How old are you ! Mr." + customerName);
    }
}
