package com.syj.demo.thread._2020.pcDemo;

/**
 * @Description 生产者
 * @Date 2020/6/6 22:25
 * @Created by shiyongjun1
 */
public class Producer {

    private String lock;
    public Producer(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObject.value.equals("")) {
                    lock.wait();
                }
                String value = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set的值是 " + value);
                ValueObject.value = value;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
