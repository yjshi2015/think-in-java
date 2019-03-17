package com.syj.demo.spring.proxy;

/**
 * Created by Administrator on 2019/3/17.
 */
public class CGLibTest {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImple forumService = (ForumServiceImple)proxy.getProxy(ForumServiceImple.class);
        forumService.removeTopic(123);
    }
}
