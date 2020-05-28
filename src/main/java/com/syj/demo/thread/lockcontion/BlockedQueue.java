package com.syj.demo.thread.lockcontion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {

    final Lock lock = new ReentrantLock();
    //
}
