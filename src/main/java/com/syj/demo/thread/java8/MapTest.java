package com.syj.demo.thread.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * map函数可以说是函数式编程里最重要的一个方法了。map的作用是将一个对象变换为另外一个。
 */
public class MapTest {

    public static void main(String[] args) {
        List<Double> list = Arrays.asList(10.0, 20.0, 30.0);
        System.out.println("=========before");
        list.forEach(n -> System.out.println(n));
        List<Double> after = map(list);
        System.out.println("=========after");
        after.forEach(n -> System.out.println(n));
    }

    public static List<Double> map(List<Double> list) {
        return list.stream().map(x -> x + x * 0.05).collect(Collectors.toList());
    }
}
