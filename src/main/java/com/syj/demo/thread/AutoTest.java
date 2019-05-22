package com.syj.demo.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子类在并发场景下是线程安全的,它的执行效果跟互斥锁是一样的,但性能上优于互斥锁,因为它是无锁的,
 * 所以就没有了加锁/阻塞/释放锁/线程切换这样耗性能的操作.它的实现是借助了CPU的CAS(compare and
 * swap)指令,保证了操作是原子性的.
 *
 * CAS是类似于乐观锁的一种机制,但原子类对它进行了进一步的优化:自旋,保证了一定能达到预期的结果(
 * 非常类似于WPS项目中分配券码的逻辑哦).可以看下面的例子,结果一定是预期的结果
 *
 * 原子类 = CAS + 自旋,但同样有问题,就是ABA的问题,就是线程1要把value值改为A,此时线程2把value
 * 的值改为了B,线程3把value的值又改回了A,虽然线程1在执行的时候,value是预期的,但其实它已经发生
 * 了变化,针对ABA的问题,解决方案就是加版本号
 *
 * 原子类不仅有基础数据类型,还有对象/数组
 */
public class AutoTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong val = new AtomicLong(0);
        System.out.println("=======线程开始前的值:" + val.get());
        for (int i=0; i<10; i++) {
            new Thread(() -> {
                    for(int j=0; j<1000; j++) {
                        val.incrementAndGet();
                    }
            }, "线程"+i).start();
        }

        //一定要等待所有线程执行完毕!!!
        Thread.sleep(5000);
        System.out.println("=======线程结束后的值:" + val.get());
    }

}
