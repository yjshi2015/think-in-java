package com.syj.demo.designPattern.STM;

@FunctionalInterface
public interface TxnRunnable {

    void run(Txn txn);
}
