package com.syj.demo.thread._2020.pcDemo;

/**
 * @Description 消费者
 * @Date 2020/6/6 22:29
 * @Created by shiyongjun1
 */
public class Consumer {
    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    lock.wait();
                }
                System.out.println("get的值是 ：" + ValueObject.value);
                ValueObject.value = "";
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
