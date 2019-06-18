package com.syj.demo.thread.immutable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 父类中的子类,即使子类为不可变类,对子类引用的修改在多线程中并不能
 * 保证可见性和原子性.
 *
 * 如果要保证子类的可见性,将子类声明为volatile变量
 *
 * 如果要保证子类的原子性,那么可以通过原子类来实现
 *
 * 下面的例子是库存类的原子化实现,就是通过原子类解决了不可变对象引用
 * 的原子性问题
 */
public class SafeWM {

    class VMRange {
        final int upper;
        final int lower;
        VMRange(int upper, int lower) {
            this.upper = upper;
            this.lower = lower;
        }
    }

    final AtomicReference<VMRange> rf = new AtomicReference<>(new VMRange(0, 0));

    // 设置库存上限
    void setUpper(int v) {
        while (true) {
            VMRange or = rf.get();
            // 检查参数合法性
            if (v < or.lower) {
                throw new IllegalArgumentException();
            }
            VMRange nr = new VMRange(v, or.lower);
            if (rf.compareAndSet(or, nr)) {
                return;
            }
        }
    }
}
