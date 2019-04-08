package com.syj.demo.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2019/3/22.
 */
public class ThreadTest {

    public static void main(String[] args) {

        while (true) {
            synchronized (ThreadTest.class) {
                try {
                    ThreadTest.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
