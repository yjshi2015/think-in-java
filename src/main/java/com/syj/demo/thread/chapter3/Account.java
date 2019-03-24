package com.syj.demo.thread.chapter3;

/**
 * @desc 占有且等待方式
 * 这种操作保证了A给B转账， C给D转账可以并行，但同时导致了发生死锁的风险，
 * 即同一时刻，线程1进行A给B转账，线程2进行B给A转账，所以会发生以下的场景：
 * 某一时刻，线程1拿到了A对象的锁，线程2拿到了B对象的锁，这时线程1去争夺B对象的
 * 锁，发现已被线程2占用，于是线程1阻塞，线程2去争夺A对象的锁，发现已经被线程1
 * 占用，于是线程2阻塞，最终形成相互等待的死循环
 */
public class Account {

    private Integer balance;

    public boolean transfer(Account target, Integer num) {
        synchronized (this) {
            synchronized (target) {
                if (balance > num) {
                    this.balance -= num;
                    target.balance += num;
                    return true;
                }
                return false;
            }
        }
    }
}
