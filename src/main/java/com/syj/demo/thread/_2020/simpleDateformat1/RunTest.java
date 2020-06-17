package com.syj.demo.thread._2020.simpleDateformat1;

import java.text.SimpleDateFormat;

/**
 * @Description 测试类
 * @Date 2020/6/14 13:48
 * @Created by shiyongjun1
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
