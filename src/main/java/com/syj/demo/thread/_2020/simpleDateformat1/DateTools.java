package com.syj.demo.thread._2020.simpleDateformat1;

import java.text.SimpleDateFormat;

/**
 * @Description 日期工具
 * @Date 2020/6/14 13:20
 * @Created by shiyongjun1
 */
public class DateTools {

    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();

    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(pattern);
            t1.set(sdf);
        }
        return sdf;
    }
}
