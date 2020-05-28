package com.syj.demo.designPattern.STM;

/**
 * 所有对数据的读写操作,一定是在一个事务里.TxnRef这个类负责完成事务内的读写操作的入口,
 * 具体的实现是委托给了接口Txn,Txn代表的是读写操作所在的当前事务,内部持有的curRef
 * 代表的是系统中的最新值
 *
 */
public class TxnRef<T> {
    // 当前数据,带版本号
    volatile VersionRef curRef;
    // 构造方法
    public TxnRef(T value) {
        this.curRef = new VersionRef(value, 0L);
    }
    // 获取当前事务中的数据
    public T getValue(Txn txn) {
        return txn.get(this);
    }

    // 在当前事务中设置数据
    public void setValue(T value, Txn txn) {
        txn.set(this, value);
    }
}
