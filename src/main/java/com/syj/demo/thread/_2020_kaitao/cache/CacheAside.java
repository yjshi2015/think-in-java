package com.syj.demo.thread._2020_kaitao.cache;


import com.google.common.cache.Cache;

/**
 * Created by Administrator on 2020/6/21.
 * 缓存的使用有2种模式：
 * Cache-Aside：即业务代码围绕着缓存写，如下例子：
 * Cache-As-SoR：把缓存作为SOR，即数据源系统
 */
public class CacheAside {

    private static Cache localCache = null;

    // Cache-Aside即业务代码围绕着Cache写，是由业务代码直接维护缓存
    // 这种方式，适合使用AOP模式去实现
    public static void main(String[] args) {

    }


    // 读的场景
    public Object getFromCache(String key) {
        // 1.先从缓存中获取数据
        Object value = localCache.getIfPresent(key);
        // 2.如果缓存没有命中，则回源到SOR获取源数据
        if (value == null) {
            value = "从DB中获取数据";
            // 将数据放入缓存，下次可从缓存中直接获取
            localCache.put(key, value);
        }
        return value;
    }


    // 写的场景
    public void setCache(String key, Object value) {
        // 1.将数据写到SOR
//        writeDB(key, value);
        // 2.同步写缓存，或者将缓存置为失效
        localCache.put(key, value);
//        localCache.invalidate(key);
    }
}
