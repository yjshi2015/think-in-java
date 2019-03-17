package com.syj.demo.spring.alliance;

/**
 * Created by Administrator on 2019/3/17.
 */
public class NavieWaiter implements Waiter {
    @Override
    public void greetTo(String name) {
        System.out.println("greet to " + name);
    }

    @Override
    public void serviceTo(String name) {
        System.out.println("service to " + name);
    }
}
