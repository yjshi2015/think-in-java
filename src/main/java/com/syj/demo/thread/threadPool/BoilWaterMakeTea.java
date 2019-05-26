package com.syj.demo.thread.threadPool;

import java.util.concurrent.*;

public class BoilWaterMakeTea {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        T2Task t2Task = new T2Task();
        FutureTask<String> t2FutureTask = new FutureTask<>(t2Task);

        T1Task t1Task = new T1Task(t2FutureTask);
        FutureTask<String> t1FutureTask = new FutureTask<>(t1Task);

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(t1FutureTask);
        pool.submit(t2FutureTask);

        String result = t1FutureTask.get();
        System.out.println("result : " + result);
    }


    //T1Task 需要执行的任务:洗水壶,烧开水,泡茶
    static class T1Task implements Callable<String> {
        private FutureTask<String> t2Task;
        T1Task(FutureTask<String> t2Task) {
            this.t2Task = t2Task;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶....");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1:烧开水....");
            TimeUnit.SECONDS.sleep(5);

            String tea = t2Task.get();
            System.out.println("T1:泡茶....");
            TimeUnit.SECONDS.sleep(1);
            return "请喝" + tea;
        }
    }

    //T2Task 需要执行的任务:洗茶壶/洗茶杯/拿茶叶
    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶....");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2:洗茶杯....");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2:拿茶叶....");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }

}
