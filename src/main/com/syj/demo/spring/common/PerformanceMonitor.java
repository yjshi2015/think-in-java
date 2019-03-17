package main.com.syj.demo.spring.common;

/**
 * Created by Administrator on 2019/3/17.
 */
public class PerformanceMonitor {

    static ThreadLocal<MethodMonitor> map = new ThreadLocal<>();

    public static void begin(String methodName) {
        System.out.println(methodName + " begin monitor");
        MethodMonitor methodMonitor = new MethodMonitor(methodName);
        map.set(methodMonitor);
    }

    public static void end() {
        MethodMonitor methodMonitor = map.get();
        System.out.println(methodMonitor.getMethodName() + " end monitor");
        methodMonitor.printTime();
    }
}
