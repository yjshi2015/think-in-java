package com.syj.demo.thread._2020_kaitao.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/21.
 * 缓存回收策略：
 * 1.基于空间，即设置缓存的存储空间，如设置为100M，当达到存储空间上限时，按照一定的策略移除数据。
 * 2.基于容量，即设置缓存的条目最大大小，如设置为100万条记录。
 * 3.基于时间：TTL(Time To Live)，即存活期，即缓存从创建到到期的一个时间段，不管缓存是否被访问过。
 *            TTI(Time To Idle)，即空闲期，即缓存多久没有被访问后移除缓存。
 *
 * 缓存回收算法：
 * 1.FIFO(First In First Out):先进先出算法，即先放进缓存的最先被移除。
 * 2.LRU(Least Recently Used):最近最少使用算法，即缓存使用时间距离现在最久的最先被删除。目前是Guava的默认回收算法
 * 3.LFU(least Frequently Used):使用频次最低算法，即一定时间内使用次数最少的最先删除。
 */
public class LocalCacheInitService implements InitializingBean{

    // 分布式缓存5s过期
    private static Integer CATEGORY_REMOTE_EXPIRES_IN_SECONDS = 5;

    // 本地缓存的map集合
    private static Map<String, Cache<?, ?>> cacheMap = new HashMap<>();

    // 本地商品类目缓存
    private static String CATEGORY_KEY = "category_key";

    @Override
    public void afterPropertiesSet() throws Exception {
        // 商品类目缓存
        Cache<String, Object> categoryCache = CacheBuilder.newBuilder()
                .softValues()
                // 基于容量的缓存回收策略，当超过容量时，按照LRU算法进行回收
                .maximumSize(1000000)
                // 设置TTL（time to live）,缓存在给定的时间内没有写（创建/覆盖）后，则被回收
                // 本地缓存失效时间，是分布式缓存失效时间的一半，防止本地缓存数据缓存时间太长，造成多实例间的缓存不一致
                .expireAfterWrite(CATEGORY_REMOTE_EXPIRES_IN_SECONDS / 2, TimeUnit.SECONDS)
                // 设置TTI（time to idle）,缓存在给定的时间内没有读写，则被回收。
                // 如果是热点数据，则将一直不过期，可能会导致脏数据存在很长时间，因此不推荐该方式！！！
//                .expireAfterAccess(CATEGORY_REMOTE_EXPIRES_IN_SECONDS / 2, TimeUnit.SECONDS)
                .build();

        cacheMap.put(CATEGORY_KEY, categoryCache);
    }

}
