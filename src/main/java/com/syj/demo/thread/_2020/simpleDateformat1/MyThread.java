package com.syj.demo.thread._2020.simpleDateformat1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 线程类
 * @Date 2020/6/14 13:26
 * @Created by shiyongjun1
 */
public class MyThread extends Thread {

    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date date = DateTools.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
            String newDateString = DateTools.getSimpleDateFormat("yyyy-MM-dd").format(date);
            if (!dateString.equals(newDateString)) {
                System.out.println("ThreadName=" + this.getName() + " 报错了 日期字符串：" + dateString + " 转化成了" + newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
