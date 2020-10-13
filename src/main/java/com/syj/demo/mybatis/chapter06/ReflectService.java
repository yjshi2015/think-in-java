package com.syj.demo.mybatis.chapter06;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/10/13.
 * 反射，使Java的可配置性更加灵活
 */
public class ReflectService {

    public void sayHello(String name) {
        System.out.println("hello " + name);
    }

    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {

        // 通过反射创建ReflectService对象
        Object service = Class.forName(ReflectService.class.getName()).newInstance();
        // 获取服务方法
        Method method = service.getClass().getMethod("sayHello", String.class);
        // 反射调用方法
        method.invoke(service, "syj");
    }
}
