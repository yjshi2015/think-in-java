package com.syj.demo.thread._2020_kaitao.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/6/21.
 */
public class CacheAsSoR {


    private static Cache localCache = null;

    // Cache-As-SOR即把Cache看做SoR，所有操作都是对Cache进行，然后Cache再委托给SoR进行真实的读写。
    // 以下为Read-Through模式，业务代码首先调用Cache，如果Cache不命中，则由Cache回源到SoR，只需要
    // 配置CacheLoader组件来回源到SoR加载源数据。
    // 目前Guava只实现了Read-Through，即查询的场景。
    // 至于Write-Through穿透写模式--业务代码首先调用Cache写（新增、修改）数据，然后交由Cache负责写
    // 缓存和写SOR，Guava Cache没有提供支持，Ehcache 3.x支持该模式
    public static void main(String[] args) {
        localCache = CacheBuilder.newBuilder()
                .softValues()
                .maximumSize(1000000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                // 如果缓存没命中，回源后，再写入缓存
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(final String skuNo) throws Exception {
                        return "从DB中获取记录";
                    }
                });
    }
}
