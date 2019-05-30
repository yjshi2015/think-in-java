package com.syj.demo.thread.lock;

public class TransferTask implements Runnable {

    private Account source;
    private Account target;

    TransferTask(Account source, Account target) {
        this.source = source;
        this.target = target;
    }


}
