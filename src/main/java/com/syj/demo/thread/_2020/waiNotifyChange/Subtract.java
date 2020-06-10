package com.syj.demo.thread._2020.waiNotifyChange;

/**
 * @Description 删除元素的业务实现
 * @Date 2020/6/6 20:48
 * @Created by shiyongjun1
 */
public class Subtract {

    private String lock;

    public Subtract(String lock) {
        this.lock = lock;
    }

    public void substract() {
        try {
            synchronized (lock) {
//                注意：使用if会导致线程被唤醒后，运行条件发生变化，所以必须使用while，保证再次校验下条件，
//                方能继续执行，切记！！！
//                if (ValueObject.list.size() == 0) {
//                    System.out.println("wait begin ThreadName=" + Thread.currentThread().getName());
//                    lock.wait();
//                    System.out.println("wait end ThreadName=" + Thread.currentThread().getName());
//                }
                while (ValueObject.list.size() == 0) {
                    System.out.println("wait begin ThreadName=" + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName=" + Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size=" + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
