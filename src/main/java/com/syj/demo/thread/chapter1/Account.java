package com.syj.demo.thread.chapter1;

/**
 * @desc 模拟银行账号间的转账操作，要考虑transfer操作的安全和有效性
 *
 * 1.因为balance是共享变量，所以该方法不是线程安全的
 * 2.假设A、B、C 3个账号各有余额200元，线程1执行A给B转100，线程2执行B给C转100，
 * 会发生什么情况呢？
 * 情况一：线程1、线程2均读到B有200元，线程1执行后A有100元，B有300元，线程2执行后，
 *    覆盖了线程1的执行结果，导致B有100元，C有300元，所以就是A 100、B 100、C 300
 * 情况二：线程1、线程2均读到B有200元，线程2执行后B有100元，C有300元，线程1执行后，
 *    覆盖了线程2的执行结果，导致B有300元，A有100元，所以就是A 100、B 300、C 300
 */
public class Account {

    private Integer balance;

    //转给target金额num元
    public boolean transfer(Account target, Integer num) {
        if (balance > num) {
            this.balance -= num;
            target.balance += num;
            return true;
        }
        return false;
    }
}
