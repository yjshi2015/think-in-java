package com.syj.demo.spring.alliance;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/17.
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnObj, Method method,
                               Object[] args, Object obj) throws Throwable {
        System.out.println("please enjoy yoursele!");
    }
}
