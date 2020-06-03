package com.syj.demo.thread._2020.waitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/6/3.
 */
public class MyList {

    private static List list = new ArrayList<>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }
}
