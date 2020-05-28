package com.syj.demo.thread.lock;

/**
 * 基本类型的包装类是不可变的
 */
public class PackingClass {

    public static void main(String[] args) {
        Inner a = new Inner(1L);
        Inner b = new Inner(1l);
        Inner c = new Inner(2L);

        new Thread(() -> { a.test(); }, "线程a").start();
        new Thread(() -> { b.test(); }, "线程b").start();
        new Thread(() -> { c.test(); }, "线程c").start();
    }

    static class Inner {

        Long num;

        Inner(Long num) {
            this.num = num;
        }

        void test() {
            //加锁后不释放,以验证同样的锁而言,只有1个线程能拿到,其他线程不能拿到
            synchronized (num) {
                System.out.println("线程 "+Thread.currentThread().getName()+" 对num: "+num+" 加锁成功");
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
