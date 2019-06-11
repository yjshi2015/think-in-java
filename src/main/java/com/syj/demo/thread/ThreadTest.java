package com.syj.demo.thread;

import com.sun.xml.internal.ws.api.pipe.Fiber;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;

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
