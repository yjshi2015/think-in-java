package main.com.syj.demo.spring.proxy;

import main.com.syj.demo.spring.aspect.ForumService;
import main.com.syj.demo.spring.common.PerformanceMonitor;

/**
 * Created by Administrator on 2019/3/17.
 */
public class ForumServiceImple implements ForumService {

    @Override
    public void removeTopic(int topicId) {
        //模拟业务逻辑
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(this.getClass() + " removeTopic发生异常");
        }
    }

    @Override
    public void removeForum(int forumId) {
        //模拟业务逻辑
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(this.getClass() + " removeForum发生异常");
        }
    }
}
