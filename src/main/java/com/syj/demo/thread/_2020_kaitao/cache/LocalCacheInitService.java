package com.syj.demo.thread._2020_kaitao.cache;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/21.
 * 缓存回收策略：
 * 1.基于空间，即设置缓存的存储空间，如设置为100M，当达到存储空间上限时，按照一定的策略移除数据。
 * 2.基于容量，即设置缓存的条目最大大小，如设置为100万条记录。
 * 3.基于时间：TTL(Time To Live)，即存活期，即缓存从创建到到期的一个时间段，不管缓存是否被访问过。
 * TTI(Time To Idle)，即空闲期，即缓存多久没有被访问后移除缓存。
 * <p>
 * 缓存回收算法：
 * 1.FIFO(First In First Out):先进先出算法，即先放进缓存的最先被移除。
 * 2.LRU(Least Recently Used):最近最少使用算法，即缓存使用时间距离现在最久的最先被删除。目前是Guava的默认回收算法
 * 3.LFU(least Frequently Used):使用频次最低算法，即一定时间内使用次数最少的最先删除。
 */
public class LocalCacheInitService implements InitializingBean {

    // 分布式缓存5s过期
    private static Integer CATEGORY_REMOTE_EXPIRES_IN_SECONDS = 5;

    // 本地缓存的map集合
    private static Map<String, Cache<?, ?>> cacheMap = new HashMap<>();

    // 异步写远程缓存的线程池
    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    // 本地商品类目缓存
    private static String CATEGORY_KEY = "category_key";
    // 读写本地缓存开关
    private static boolean writeLocalCache = true;
    private static boolean readLocalCache = true;
    // 读写分布式缓存开关
    private static boolean writeRemoteCache = true;;
    private static boolean readRemoteCache = true;;

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

    // 先写本地缓存，如果需要写分布式缓存，则异步更新分布式缓存（Redis）
    public static void set(final String key, final Object value, final int remoteCacheExpiresInSeconds)
            throws RuntimeException {
        if (value == null) {
            return;
        }

        // 复制值对象
        // 本地缓存是引用，分布式缓存需要序列化
        // 如果不复制的话，假设数据更改后将造成本地缓存与分布式缓存不一致
        final Object finalValue = null;
//        finalValue = copy(value);

        // 如果配置了写本地缓存，则根据Key获得相关的本地缓存，然后写入
        if (writeLocalCache) {
            Cache localCache = cacheMap.get(CATEGORY_KEY);
            if (localCache != null) {
                localCache.put(key, finalValue);
            }
        }

        // 如果配置了不写分布式缓存，则结束
        if (!writeRemoteCache) {
            return;
        }

        // 异步写分布式缓存，目的是尽快返回用户请求。即使分布式缓存更新比较慢，又产生了回源，也可以命中本地缓存
        pool.submit(() -> {
            try {
                // Redis.put(key, value)
                TimeUnit.SECONDS.sleep(3);
                System.out.println("finalValue" + JSONObject.toJSONString(finalValue));
            } catch (Exception e) {
                System.out.println("update redis cache error, key:" + key);
            }
        });
    }

    // 读缓存，先读取本地缓存，不命中的话再批量查询分布式缓存
    private Map innerMget(List<String> keys, List<Class> types) throws Exception {
        Map<String, Object> result = Maps.newHashMap();
        List<String> missKeys = Lists.newArrayList();
        List<Class> missTypes = Lists.newArrayList();
        // 如果配置了读取本地缓存，则先读本地
        if (readLocalCache) {
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                Class type = types.get(i);
                Cache localCache = cacheMap.get(CATEGORY_KEY);
                if (localCache != null) {
                    Object value = localCache.getIfPresent(key);
                    result.put(key, value);
                    if (value == null) {
                        missKeys.add(key);
                        missTypes.add(type);
                    }
                } else {
                    missKeys.add(key);
                    missTypes.add(type);
                }
            }
        }
        // 如果配置了不读取分布式缓存，则返回
        if (!readRemoteCache) {
            return result;
        }
        final Map<String, Object> missResult = Maps.newHashMap();

        // 对key进行分区，不要一次性批量调用太大
        final List<List<String>> keysPage = Lists.partition(missKeys, 10);
        List<Future<Map<String, String>>> pageFuture = Lists.newArrayList();

        try {
            // 批量获取分布式缓存数据
            for (List<String> partitionKeys : keysPage) {
                pageFuture.add(pool.submit(() -> {
                    // Redis批量获取数据
                    // Resis.mget(partitionKeys);
                    return Maps.newHashMap();
                }));
            }
            for (Future<Map<String, String>> future : pageFuture) {
                missResult.putAll(future.get(3000, TimeUnit.MILLISECONDS));
            }
        } catch (Exception e) {
            // 异常后，一定要取消掉任务
            pageFuture.forEach(future -> future.cancel(true));
            throw e;
        }

        // 合并result和MissResult
        result.putAll(missResult);
        return result;
    }

    public static void main(String[] args) {
        String key = "1级类目";
        List<String> list = Lists.newArrayList("生鲜", "家电");
        set(key, list, 1);
        // 值发生了变化，则会造成本地缓存与分布式缓存不一致
        list.add("健康");
        System.out.println("list:" + JSONObject.toJSONString(list));
    }

}
