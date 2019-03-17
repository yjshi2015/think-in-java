package main.com.syj.demo.spring.aspect.impl;

import main.com.syj.demo.spring.aspect.ForumService;
import main.com.syj.demo.spring.common.PerformanceMonitor;

/**
 * Created by Administrator on 2019/3/17.
 */
public class ForumServiceImpl implements ForumService {

    @Override
    public void removeTopic(int topicId) {
        PerformanceMonitor.begin(Thread.currentThread().getStackTrace()[1].getMethodName());
        //模拟业务逻辑
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(this.getClass() + " removeTopic发生异常");
        }

        PerformanceMonitor.end();
    }

    @Override
    public void removeForum(int forumId) {

    }
}
