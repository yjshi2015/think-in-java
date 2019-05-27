package com.syj.demo.thread.java8;

import java.util.Arrays;
import java.util.List;

/**
 * map的作用是将一个对象变为另外一个，而reduce实现的则是将所有值合并为一个。
 */
public class ReduceTest {

    public static void main(String[] args) {
        mapReduce();
    }

    public static void mapReduce() {
        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
        double allCost = cost.stream().map(x -> x + x * 0.05).reduce((sum, x) -> sum + x).get();
        System.out.println(allCost);
    }
}
