package com.syj.demo.spring.test;

import com.syj.demo.spring.aspect.ForumService;
import com.syj.demo.spring.aspect.impl.ForumServiceImpl;

/**
 * Created by Administrator on 2019/3/17.
 */
public class ApsectTest {

    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeTopic(123);
    }
}
