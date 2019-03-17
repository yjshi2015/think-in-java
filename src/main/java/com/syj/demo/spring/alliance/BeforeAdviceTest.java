package com.syj.demo.spring.alliance;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by Administrator on 2019/3/17.
 */
public class BeforeAdviceTest {

    public static void main(String[] args) {
        Waiter target = new NavieWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();

        //spring提供的代理工厂
        ProxyFactory pf = new ProxyFactory();
        //设置代理目标类
        pf.setTarget(target);
        //为代理目标添加增强
        pf.addAdvice(advice);
        //生成代理实例
        Waiter proxy = (Waiter) pf.getProxy();

        proxy.greetTo("SHI ");
        proxy.serviceTo("SHI ");
    }
}
