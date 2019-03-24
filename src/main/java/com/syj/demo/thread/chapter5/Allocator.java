package com.syj.demo.thread.chapter5;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 账本管理员
 */
public class Allocator {

    private List<Account> accounts = new ArrayList<>();

    private Allocator(){}

    private static volatile Allocator singleInstance = null;

    public static Allocator getSingleInstance() {
        if (singleInstance == null) {
            synchronized (Allocator.class) {
                if (singleInstance == null) {
                    singleInstance = new Allocator();
                }
            }
        }
        return singleInstance;
    }

    /**
     * 一次性申请所有资源，如果申请不到，线程阻塞，等待，同时释放CPU，这
     * 里使用while循环为经典范式，唤醒线程和线程开始执行并不总是在一个时
     * 间点，线程执行时可能之前满足的条件已经不再满足了，所以线程执行后通
     * 过while循环再做一次判断
     *
     * @param source
     * @param target
     * @return
     */
    public synchronized boolean apply(Account source, Account target) {
        while (accounts.contains(source) || accounts.contains(target)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        accounts.add(source);
        accounts.add(target);
        return true;
    }

    //释放完资源后，通过notifyall唤醒所有等待队列
    public synchronized void free(Account source, Account target) {
        accounts.remove(source);
        accounts.remove(target);
        notifyAll();
    }
}
