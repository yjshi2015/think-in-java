package com.syj.demo.thread._2020.simpleDataformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description test
 * @Date 2020/6/14 10:50
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
            Date date = sdf.parse(dateString);
            String newDateString = sdf.format(date);
            if (!newDateString.equals(dateString)) {
                System.out.println("ThreadName=" + this.getName() + "报错了 日期字符串：" + dateString
                        + " 被转化成：" + newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
