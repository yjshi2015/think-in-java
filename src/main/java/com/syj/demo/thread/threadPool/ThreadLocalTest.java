package com.syj.demo.thread.threadPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    static class SafeDateFormat {
        static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(
                () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
        );

        static DateFormat get() {
            return tl.get();
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                DateFormat sf = SafeDateFormat.get();
                Date now = new Date();
                System.out.println("线程对应的时间为:"+sf.format(now)+",实际时间:"+now.getTime());
            }, "线程"+i).start();
        }
    }
}
