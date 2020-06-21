package com.syj.demo.thread._2020_kaitao.cache;

import com.google.common.cache.Cache;

/**
 * Created by Administrator on 2020/6/21.
 */
public class NullCache {

    // 定义空对象
    private static final String NULL_STRING = new String();

    // 本地缓存
    private static Cache<String, String> localCache = null;

    public static void main(String[] args) {

        System.out.println("到底是啥" + NULL_STRING.toString() + "。");
    }

    public static Object getFromLocalCache(String key) {
        String value = localCache.getIfPresent(key);

        // DB中没有数据，返回Null
        if (value == NULL_STRING) {
            return null;
        }

        if (value != null) {
            return value;
        }

        // 如果缓存为null，则走库查询
        String dbValue = "从数据库查询";

        // 如果DB中没有数据，则将其封装为NULL_STRING
        if (dbValue == null) {
            dbValue = NULL_STRING;
        }

        // 缓存中放入空对象
        // 通过这种方式，可以避免当key对应的数据在DB中不存在时，频繁查询DB的情况
        localCache.put(key, dbValue);

        return dbValue;

    }
}
