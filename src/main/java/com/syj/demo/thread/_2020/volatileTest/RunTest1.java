package com.syj.demo.thread._2020.volatileTest;

/**
 * Created by Administrator on 2020/6/2.
 *
 * 关键字volatile虽然增加了实例变量在多个线程之间的可见性，但它并不具备同步性，所以也就不具备原子性。
 *
 * 关键字volatile提示线程每次从主内存中读取变量，而不是从工作内存中读取，这样就保证了实例变量的可见性。
 * 但需要注意的是：如果修改了实例变量中的数据，比如i++，也就是i=i+1，则这样的操作并不是一个原子性，也
 * 就是非线程安全的。
 *
 * 表达式i++的操作步骤如下：
 * 1.从内存中读取i的值；
 * 2.计算i+1的值；
 * 3.将计算后的新值写到内存中；
 * 假如在第2步计算值的时候，另外一个线程也修改了i的值，那么这时候就会出现脏数据。
 * 所以要使用i++操作，推荐使用原子类，见RunTest3
 *
 * 关键字volatile的使用场景是在多个线程中感知实例变量被改变了，并且可以获得最新的值使用，也就是用多
 * 线程读取共享变量是可以获得最新值使用（推荐修饰Boolean这样的属性，而非i++）
 *
 *
 */
public class RunTest1 {

    public static void main(String[] args) {
        RunThead1[] myThreadArr = new RunThead1[100];
        for (int i=0; i<100; i++) {
            // 变量为RunThead1中的静态变量，所以100个线程共享1份变量
            myThreadArr[i] = new RunThead1();
        }

        for (int i=0; i<100; i++) {
            myThreadArr[i].start();
        }
    }
}
