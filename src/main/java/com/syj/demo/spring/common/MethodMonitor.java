package com.syj.demo.spring.common;

import lombok.Data;

/**
 * Created by Administrator on 2019/3/17.
 */
@Data
public class MethodMonitor {

    private String methodName;
    private long beginTime;
    private long endTime;

    MethodMonitor(String methodName) {
        this.methodName = methodName;
        beginTime = System.currentTimeMillis();
    }

    void printTime() {
        endTime = System.currentTimeMillis();
        System.out.println("方法" +methodName+ " 总共耗时：" + (endTime-beginTime)/1000 + " 秒");
    }


}
