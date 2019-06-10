package com.syj.demo.thread.blaking;

import java.util.Set;
import java.util.concurrent.*;

/**
 * 在RPC框架中,本地路由表是要和注册中心进行信息同步的,应用启动时候,会将应用依赖服务的路由表
 * 从注册中心同步到本地路由表中,如果应用重启的时候注册中心宕机,会导致该应用依赖的服务均不可用,
 * 因为找不到依赖服务的路由表.
 *
 * 为了防止这种情况出现,RPC框架可以将本地路由表保存到本地文件中,如果重启时注册中心宕机,那么
 * 就从本地文件中恢复重启前的路由表.这也是一种降级方案
 *
 * 该例子采用balking模式,采用volatile实现,而非synchronize锁.所以性能要比synchronize方式的要高
 *
 * 注意:使用volatile的前提是对原子性没哟要求
 */
public class RouterTable {
    // key:接口名
    // value:路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();

    // 路由表是否变化
    volatile boolean changed;

    //将路由表写入本地文件的定时任务线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    // 启动定时任务,将变更后的路由表写入本地文件
    public void startLocalSaver() {
        ses.scheduleWithFixedDelay(() -> {
            autoSave();
        }, 1, 1, TimeUnit.MINUTES);
    }

    // 保存路由表到本地文件
    void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        // 将路由表保存到本地文件,省略其实现
//        this.save2Local();
    }

    // 删除路由
    public void remove(Router router) {
        Set<Router> set = rt.get(router.getIface());
        if (set != null) {
            set.remove(router);
            // 路由表已发生变化
            this.changed = true;
        }
    }

    // 增加路由
    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(
                router.getIface(), r -> new CopyOnWriteArraySet<>()
        );
        set.add(router);
        // 路由表已发生变化
        this.changed = true;
    }
}
