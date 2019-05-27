package com.syj.demo.thread.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LamdaTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c","d");

        //Java1.8之前的遍历
        for (String item: list) {
            System.out.println(item);
        }

        //Java1.8之后的遍历
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s:"+s);
            }
        });

        //Java1.8之后,采用lamda方式遍历
        list.forEach(n -> System.out.println(n));

        System.out.println("::为方法引用");
        list.forEach(System.out::println);
    }
}
