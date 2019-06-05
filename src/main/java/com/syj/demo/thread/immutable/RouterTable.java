package com.syj.demo.thread.immutable;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class RouterTable {

    public static void main(String[] args) {
        ConcurrentHashMap<String, CopyOnWriteArraySet<String>> rt = new ConcurrentHashMap<>();
        Set<String> set = rt.computeIfAbsent("123", r-> new CopyOnWriteArraySet<>());

        HashMap hm = new HashMap();
    }

}
