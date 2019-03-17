package main.com.syj.demo.spring.proxy;

import main.com.syj.demo.spring.common.PerformanceMonitor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/17.
 * @desc CGLib代理，为一个类创建子类
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    //通过字节码技术动态创建子类
    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //拦截父类所有方法的调用
    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        PerformanceMonitor.begin(obj.getClass().getName()
                +"."+method.getName());

        //在代理实例上反射调用目标方法，会递归调用到代理方法，会陷入死循环
//        Object result = proxy.invoke(obj, args);
        //在代理实例上反射调用到代理方法来调用到目标方法
        Object result = proxy.invokeSuper(obj, args);

        PerformanceMonitor.end();
        return result;
    }
}
