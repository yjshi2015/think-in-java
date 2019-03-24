package com.syj.demo.thread.chapter5;

/**
 * Created by Administrator on 2019/3/24.
 */
public class Account {

    private Integer balance;
    private Allocator allocator = Allocator.getSingleInstance();

    public boolean transfer(Account target, Integer num) {
        //注意此处不再使用循环，如果未返回结果，则线程阻塞
        allocator.apply(this, target);

        boolean flag = false;
        try {
            synchronized (this) {
                synchronized (target) {
                    if (this.balance > num) {
                        this.balance -= num;
                        target.balance += num;
                        flag = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            allocator.free(this, target);
            return flag;
        }
    }

}
