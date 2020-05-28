package com.syj.demo.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该demo的存在的问题:
 */
public class Account {

    private int balance;
    private final Lock lock = new ReentrantLock();

    //转账
    void transfer(Account tar, int amt) {
        while (true) {
            if (this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Account zs = new Account();
        Account ls = new Account();
        Account ww = new Account();

        //张三同时给李四/王五转账,因为有while循环,所以不会有问题


    }
}
