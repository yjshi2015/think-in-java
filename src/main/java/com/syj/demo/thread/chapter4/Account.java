package com.syj.demo.thread.chapter4;

/**
 * @desc 破坏占有且等待模式，统一向账本管理员申请所有资源
 * 缺点：当对同一个账号的操作并发量大的话，while循环会一直占用CPU
 */
public class Account {

    private Integer balance;

    //一定要保证账本管理员是单例模式
    private Allocator allocator = Allocator.getInstance();

    public boolean transfer(Account target, Integer num) {
        boolean flag = false;

        //直到申请到所有资源
        while (!allocator.apply(this, target));

        try {
            //实际业务场景可以会进行查询余额，所以这个地方需要再加锁
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
