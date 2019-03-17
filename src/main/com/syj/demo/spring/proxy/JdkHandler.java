package main.com.syj.demo.spring.proxy;

import main.com.syj.demo.spring.common.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/17.
 * @desc JDK代理模式,需要实现InvocationHandler接口
 * JDK代理模式在"运行期"创建"接口"的代理实例
 */
public class JdkHandler implements InvocationHandler{

    //目标业务类
    private Object target;

    public JdkHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName()+"."+method.getName());
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
