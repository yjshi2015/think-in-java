package com.syj.demo.thread.chapter4;

/**
 * @desc 破坏循环等待条件
 * 对资源进行排序，就可以破坏循环条件
 *
 */
public class Account2 {

    private Long id;
    private Integer balance;

    public boolean transfer(Account2 target, Integer num) {
        Account2 small = this;
        Account2 big = target;
        if (this.id > target.id) {
            small = target;
            big = this;
        }
        synchronized (small) {
            synchronized (big) {
               if (this.balance > num) {
                   this.balance -= num;
                   target.balance += num;
                   return true;
               }
               return false;
            }
        }
    }
}
