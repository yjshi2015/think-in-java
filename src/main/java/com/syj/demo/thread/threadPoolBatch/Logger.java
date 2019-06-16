package com.syj.demo.thread.threadPoolBatch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * 线程池中的每个线程批量执行任务,利用生产-消费模式还可以轻松的支持一种分阶段提交的应用场景.
 *
 * 写文件操作同步刷盘性能会很慢,所以对应不是很重要的数据,可以采用异步刷盘的方式.以下面日志
 * 组件为例,刷盘的时机是:
 * 1.ERROR级别的日志需要立即刷盘;
 * 2.数据积累到500条需要立即刷盘;'
 * 3.存在未刷盘数据,且5s内未曾刷盘,需要立即刷盘;
 *
 * 这个日志组件异步刷盘操作的本质其实就是一种"分阶段提交".
 */
public class Logger {

    // 用于终止日志执行的"毒丸"
    final LogMsg poisonPill = new LogMsg(LEVEL.ERROR, "");

    // 任务队列
    final BlockingQueue<LogMsg> bq = new LinkedBlockingQueue<>();
    // flush 批量
    static final int batchSize = 500;
    // 只需要1个线程写日志
    ExecutorService es = Executors.newFixedThreadPool(1);
    // 启动写日志线程
    void start() throws IOException{
        File file = File.createTempFile("foo", ".log");
        final FileWriter writer = new FileWriter(file);
        es.execute(() -> {
            try {
                // 未刷盘日志数量
                int curIdx = 0;
                long begin = System.currentTimeMillis();
                while (true) {
                    LogMsg log = bq.poll(5, TimeUnit.SECONDS);
                    // 如果是"毒丸",终止执行
                    if (poisonPill.equals(log)) {
                        break;
                    }
                    // 写日志
                    if (log != null) {
                        writer.write(log.toString());
                        ++curIdx;
                    }
                    // 如果不存在未刷盘数据,则无需刷盘
                    if (curIdx <= 0) {
                        continue;
                    }
                    // 根据规则刷盘
                    if (log != null && log.level == LEVEL.ERROR
                            || curIdx == batchSize
                            || System.currentTimeMillis() - begin > 5000) {
                        writer.flush();
                        curIdx = 0;
                        begin = System.currentTimeMillis();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 写INFO级别日志
    void info(String msg) {
        try {
            bq.put(new LogMsg(LEVEL.INFO, msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 写ERROR级别日志
    void error(String msg) {
        try {
            bq.put(new LogMsg(LEVEL.ERROR, msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 终止写日志
    public void stop() {
        // 将"毒丸"对象加入阻塞队列
        bq.add(poisonPill);
        es.shutdown();
    }

    // 日志级别
    enum LEVEL {
        INFO, ERROR
    }

    class LogMsg {
        LEVEL level;
        String msg;

        LogMsg(LEVEL lvl, String msg) {
            this.level = lvl;
            this.msg = msg;
        }

        public String toString() {
            return "省略具体实现";
        }
    }
}
