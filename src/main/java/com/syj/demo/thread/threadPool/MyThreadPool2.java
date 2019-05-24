package com.syj.demo.thread.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyThreadPool2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        List<Future<String>> result = new ArrayList<>();
        for (int i=0; i<3; i++) {
            MyTask task = new MyTask(i);
            Future<String> r = pool.submit(task);
            result.add(r);
        }

        Thread.sleep(2000);
        for (Future<String> item : result) {
            System.out.println(item.get());
        }
        pool.shutdown();
    }
}
