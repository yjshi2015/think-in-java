package com.syj.demo.designPattern.STM;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * STM事务实现类
 */
public final class STMTxn implements Txn {

    // 事务ID生成器
    private static AtomicLong txnSeq = new AtomicLong(0);

    // 当前事务所有的相关数据
    private Map<TxnRef, VersionRef> inTxMap = new HashMap<>();
    // 当前事务所有要修改的数据
    private Map<TxnRef, Object> writeMap = new HashMap<>();
    // 当前事务id
    private long txnId;
    // 构造函数
    STMTxn() {
        txnId = txnSeq.incrementAndGet();
    }

    // 获取当前事务中的数据,做快照
    @Override
    public <T> T get(TxnRef<T> ref) {
        // 将需要读取的数据,加入inTxnMap
        if (!inTxMap.containsKey(ref)) {
            inTxMap.put(ref, ref.curRef);
        }
        return (T) inTxMap.get(ref).value;
    }

    // 在当前事务中修改数据
    @Override
    public <T> void set(TxnRef<T> ref, T value) {
        // 将需要修改的数据,加入inTxnMap,针对没有读,直接写的场景
        if (!inTxMap.containsKey(ref)) {
            inTxMap.put(ref, ref.curRef);
        }
        writeMap.put(ref, value);
    }

    // 提交事务
    boolean commit() {
        synchronized (STM.commitLock) {
            // 检验是否通过
            boolean isValid = true;
            // 检验所有读过的数据是否发生过变化
            for (Map.Entry<TxnRef, VersionRef> item : inTxMap.entrySet()) {
                VersionRef curRef = item.getKey().curRef;
                VersionRef readRef = item.getValue();
                // 通过版本号校验是否发生过变化
                if (curRef.version != readRef.version) {
                    isValid = false;
                    break;
                }
            }
            // 如果校验通过,则让所有更改生效
            if (isValid) {
                writeMap.forEach((k, v) -> {
                    k.curRef = new VersionRef(v, txnId);
                });
            }
            return isValid;
        }
    }
}
