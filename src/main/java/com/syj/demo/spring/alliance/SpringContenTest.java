package com.syj.demo.spring.alliance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2019/3/17.
 */
public class SpringContenTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Waiter waiter = (Waiter) ctx.getBean("waiter");
        waiter.greetTo("ZHAO");
    }
}
