package com.syj.demo.thread._2020.simpleDataformat;

import java.text.SimpleDateFormat;

/**
 * @Description 测试入口
 * @Date 2020/6/14 10:56
 * @Created by shiyongjun1
 *
 * SimpleDateFormat在多线程环境下是有线程安全问题的。
 * 在parse(dateString)方法最后，会有一个clab.establish(calendar)方法，这个方法会清掉“全局”的calendar对象，导致其他线程执行失败
 */
public class RunTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArr = new String[]{"2019-01-12", "2020-06-18", "2020-07-14",
                "2020-09-18", "2020-11-11", "2020-06-21", "2020-09-12", "2020-08-13", "2020-09-11",
                "2010-09-18"};

        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread(sdf, dateStringArr[i]);
        }

        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }
}
