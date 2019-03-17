package com.syj.demo.spring.proxy;

import com.syj.demo.spring.aspect.ForumService;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/17.
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        ForumService target = new ForumServiceImple();

        //将目标类和横切代码编织到一起
        JdkHandler handler = new JdkHandler(target);

        //根据编织了业务代码和横切逻辑代码的InvocationHandler创建代理实例
        ForumService proxy = (ForumService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler
        );

        proxy.removeTopic(123);
        proxy.removeForum(456);
    }
}
