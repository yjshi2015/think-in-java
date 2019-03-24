package com.syj.demo.thread.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 账本管理员
 */
public class Allocator {

    private List<Account> accounts = new ArrayList<>();

    private Allocator(){}

    private static volatile Allocator singleInstance = null;

    public static Allocator getInstance() {
        if (singleInstance == null) {
            synchronized (Allocator.class) {
                if (singleInstance == null) {
                    singleInstance = new Allocator();
                }
            }
        }
        return singleInstance;
    }

    //一次性申请所有资源
    synchronized boolean apply(Account source, Account target) {
        if (accounts.contains(source) || accounts.contains(target)) {
            return false;
        }

        accounts.add(source);
        accounts.add(target);
        return true;
    }

    //一次性释放所有资源
    synchronized void free(Account source, Account target) {
        accounts.remove(source);
        accounts.remove(target);
    }
}
