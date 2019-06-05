package com.syj.demo.thread.guardedSuspension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class GuardedObject<T> {

    //受保护的对象
    T obj;
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;
    // 保存所有的GuardedOjbect
    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();
    // 静态方法创建GuardedObject
    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }
    // 根据id找到对应的Obj
    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChange(obj);
        }
    }

    // 获取受保护对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return obj;
    }

    // 事件通知方法
    void onChange(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
