package com.syj.demo.thread.blaking;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * Guarded Suspension模式:多线程版本的if,每个线程都需要满足一定的条件,即if,后才能执行,并且必须"等待直到条件为真"
 * Balking模式:多线程版本的if,每个线程都需要满足一定的条件,不过它是快速放弃的,线程并不需要那么执着
 *
 * 该例子为synchronized的Blaking模式
 */
public class AutoSaveEditor {
    // 文件是否被修改过
    boolean changed = false;
    // 定时任务线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    // 定时执行自动保存
    void startAutoSave() {
        ses.scheduleWithFixedDelay(() -> {
            autoSave();
        }, 5, 5, TimeUnit.SECONDS);
    }
    // 自动存盘操作,跟edit操作互斥
    // 使用synchronized加锁,没有在整个方法维度加锁,只是在读写共享变量changed的地方加锁,比在方法维度加锁性能要高
    void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }

        // 执行存盘操作,省略其实现
//        this.execSave();
    }
    // 编辑操作
    void edit() {
        // 省略编辑逻辑
//        this.editWord();
        change();
    }
    // 改变状态
    void change() {
        synchronized (this) {
            changed = true;
        }
    }
}
