package com.syj.demo.thread.immutable;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 不可变对象Immutability的写操作往往都是使用Copy-on-Write方法解决的(COW),当然Copy-on-Write应用领域
 * 并不局限Immutability模式.
 *
 * 看下面的例子:
 * 每次RPC调用都需要访问路由表,所以访问路由表这个操作的性能要求是很高的.不过路由表对数据的一致性要求并不
 * 高,一个服务方从上线到反馈到客户端的路由表里,即便有5秒,很多时候也是可以接受的.
 *
 * 路由表是典型的读多写少,弱一致性问题.非常适合CopyOnWriteArrayList和CopyOnWriteArraySet的应用场景
 *
 * 考虑下Router该如何设计,服务提供方的每一次上线/下线都会更新路由信息,这时候有2种选择:一种是通过更新Router
 * 的一个状态来标识,如果这样做,那么所有访问该状态位的地方都需要同步访问,很影响性能.另外一种就是采用Immutability
 * 模式,每次上线/下线都创建新的Router对象或者删除对应的Router对象.由于上线/下线的频率很低,所以后者是
 * 很好的一种选择
 */
public class RouterTable {

    // 路由信息
    public final class Router{
        private final String ip;
        private final Integer port;
        private final String iface;
        // 构造函数
        public Router(String ip, Integer port, String iface) {
            this.iface = iface;
            this.ip = ip;
            this.port = port;
        }
        // 重写equals方法
        public boolean equals(Object obj) {
            if (obj instanceof Router) {
                Router r = (Router) obj;
                return iface.equals(r.iface) && ip.equals(r.ip) && port.equals(r.port);
            }
            return false;
        }

        public int hashCode() {
            // 省略hashcode相关代码
            return 0;
        }
    }

    // key:接口名 val:路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();
    // 根据接口名获取路由表
    public Set<Router> get(String iface) {
        return rt.get(iface);
    }

    // 删除路由
    public void remove(Router router) {
        Set<Router> set = rt.get(router.iface);
        if (set != null) {
            set.remove(router);
        }
    }

    // 增加路由
    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(
                router.iface, r -> new CopyOnWriteArraySet<>()
        );
        set.add(router);
    }

}
