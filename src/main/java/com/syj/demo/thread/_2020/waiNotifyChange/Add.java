package com.syj.demo.thread._2020.waiNotifyChange;

/**
 * @Description 增加元素的业务实现
 * @Date 2020/6/6 20:46
 * @Created by shiyongjun1
 */
public class Add {

    private String lock;
    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}
