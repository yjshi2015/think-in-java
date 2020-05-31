package com.syj.demo.thread._2020.objectLockChange;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/5/31.
 *
 * 所对象的改变：只要对象不变化，即使属性变化了，运行的结果还是同步的
 */
public class RunTest1 {

    public static void main(String[] args) throws InterruptedException {
        MyServce1 myServce = new MyServce1();
        UserInfo1 userInfo1 = new UserInfo1();

        Thread t1 = new Thread(() -> myServce.methodTest(userInfo1));
        t1.setName("A");

        Thread t2 = new Thread(() -> myServce.methodTest(userInfo1));
        t2.setName("B");

        t1.start();
        TimeUnit.MILLISECONDS.sleep(50);
        t2.start();
    }

}
