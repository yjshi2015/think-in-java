package com.syj.demo.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock和synchronized的区别
 * synchronized在申请资源的时候,如果申请不到,线程就直接进入阻塞状态,而线程进入阻塞状态后,什么都干不了,
 * 也释放不了线程已经占有的,这种"抢占"的情况,很容易造成死锁
 *
 * Lock锁,对于"不可抢占"这个条件,占用部分资产的线程进一步申请其他资产时,如果申请不到,可以主动释放它占有
 * 的资源,这样"不可抢占"条件就被破坏掉了.try{获取锁} finally{释放锁},具体实现为如下3种
 *
 * 1.能够响应中断:synchronized的问题是,持有锁A后,如果尝试获取锁B失败,那么线程就进入阻塞状态,一旦发生
 * 死锁,就没有任何机会来唤醒阻塞的线程.如果阻塞线程能够响应中断信号,也就是当我们给阻塞的线程发送中断信号时,
 * 能够唤醒它,那么就有机会释放曾经持有的锁A.这样就可以破坏不可抢占条件
 * 2.支持超时:如果线程一段时间内获取不到锁,不是进入阻塞状态,而是返回一个错误,那么线程就有机会释放曾经占有
 * 的锁.也能破坏"不可抢占条件"
 * 3.非阻塞的获取锁:如果尝试获取锁失败,并不进入阻塞状态,而是直接返回
 *
 */
public class LockTest {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
    }
}
