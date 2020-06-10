package com.syj.demo.thread._2020.join3;


/**
 * @Description 启动入口
 * @Date 2020/6/7 14:34
 * @Created by shiyongjun1
 * <p>
 * join陷阱！！！
 * join()方法后面的逻辑优先执行
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            b.start();
            /**
             * 这玩意儿影响线程a、b的执行顺序，它会导致方法join()后面的代码提前运行！！！
             * 场景一：
             *
             * begin a, name = Thread-1, time = 1591512808786
             * begin a, name = Thread-1, time = 1591512813794
             *  main end 1591512813794
             * begin b, name = Thread-0, time = 1591512813794
             *   end b, name = Thread-0, time = 1591512818801
             *
             * b.join()优先抢到锁，然后立即释放，进入wait状态；
             * a线程抢到锁，打印begin
             * a线程等待5s,打印end
             * b.join()和b线程又同时抢锁，b.join()抢到，发现wait过期，然后打印main end
             * b线程获取到锁，然后打印begin
             * b线程等待5s,打印end
             *
             * 场景二（不好模拟）：
             *
             * begin a, name = Thread-1, time =
             * begin a, name = Thread-1, time =
             * begin b, name = Thread-0, time =
             *   end b, name = Thread-0, time =
             *  main end
             *
             * b.join()优先抢到锁，然后立即释放，进入wait状态
             * 线程a抢到锁，打印begin
             * 线程a等待5s
             * 线程a释放锁，打印end
             * b.join()和b线程抢锁，结果b线程抢到锁，打印begin
             * b线程等待5s
             * b线程释放锁，打印end
             * b.join()抢到锁，发现过期，打印main
             */
            b.join(2000);
            System.out.println(" main end " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
