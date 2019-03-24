package com.syj.demo.thread.chapter2;

/**
 * @desc 这种方式导致了所有转账操作都是串行的，虽然能保证线程安全，
 * 但付出的非常大的性能代价，所有的转账都必须一个个进行：即A给B转账，
 * C给D转账都要串行，不符合现实场景
 *
 */
public class Account {

    private Integer balance;

    public boolean transfer(Account target, Integer num) {
        //Account2.class是在jvm加载字节码时就创建了，肯定保证了全局唯一
        synchronized (Account.class) {
            if (balance > num) {
                this.balance -= num;
                target.balance += num;
                return true;
            }
            return false;
        }
    }
}
