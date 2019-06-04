package com.syj.demo.thread.immutable;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class MutableTest {

    public static void main(String[] args) throws InterruptedException {
        MutableClass obj = new MutableClass();

        /**
         * 可变对象存在线程安全问题
         * 不可变对象相当于为每个线程保留了变量副本,每个线程用自己的变量,互补影响;
         */
        new Thread(() -> {
            obj.set(11);
            System.out.println("obj设置为11后的值为" + obj.get());
        }, "线程1").start();

        new Thread(() -> {
            obj.set(12);
            System.out.println("obj设置为12后的值为" + obj.get());
        }, "线程2").start();


        Person person = new Person();
        person.num = 0L;
        for (int i=0; i<10; i++) {
            new Thread(person).start();
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println("num理论值是10000,实际值是"+person.num);
    }
}
