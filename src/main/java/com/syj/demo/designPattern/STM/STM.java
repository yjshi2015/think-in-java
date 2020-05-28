package com.syj.demo.designPattern.STM;

public final class STM {

    // 私有化构造方法
    private STM() {}
    // 提交数据需要用到的全局锁
    static final Object commitLock = new Object();
    // 原子化提交方法
    static void atomic(TxnRunnable action) {
        boolean commited = false;
        // 如果没有提交成功,则一直重试
        while (!commited) {
            // 创建新事务
            STMTxn txn = new STMTxn();
            // 执行业务逻辑
            action.run(txn);
            // 提交事务
            commited = txn.commit();
        }
    }
}
