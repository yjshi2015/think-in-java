package com.syj.demo.designPattern.STM;

/**
 *
 * 数据库事务发展了几十年,目前被广泛应用的是MVCC(Multi-Version Concurrency Control),也就是多版本并发控制
 *
 * MVCC可以简单的理解为数据库事务在开启的时候,会给数据库打一个快照,以后所有的读写都是基于这个快照的.当事务提
 * 交的时候,如果所有读写过的数据在该事务执行期间没有发生过变化,那么就可以提交;如果发生过变化,说明事务和其他读
 * 写的事务发生了冲突,这时候是不允许提交的.
 * MVCC的工作原理类似于乐观锁
 *
 * 很多编程语言都从数据库的事务管理中获得灵感,并且总结出了新的并发解决方案:软件事务内存(Software Transactional
 * Memory STM)
 *
 * 以下面例子来说明STM:
 * VersionedRef这个类的作用就是将对象value包装成带版本号的对象.按照MVCC理论,数据的每次修改都对应着一个唯一
 * 的版本号,所以不存在仅仅改变value或者version的情况,用不变性模式可以很好的解决这个问题,所以该类被设计为
 * 不可变类
 */
public final class VersionRef<T> {

    final T value;
    final long version;

    public VersionRef(T value, long version) {
        this.value = value;
        this.version = version;
    }
}
