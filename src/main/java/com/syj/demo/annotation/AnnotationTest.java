package com.syj.demo.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/4/8.
 */
public class AnnotationTest {

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException {
        ActivityInfo info = new ActivityInfo();
        info.setId(1);
        info.setDscEn("this is englist");
        info.setDscCn("这是中文");
        info.setDscId("Indonisia");

        ActivityBaseInfo father = info;

        Class clazz = info.getClass();
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

        for (Field field : fields) {

            //可访问私有变量
            field.setAccessible(true);
            //该字段上存在注解Language
            if (!field.isAnnotationPresent(Language.class)) {
                continue;
            }

            //获取字段类型
            Type type = field.getGenericType();
            System.out.println("type:"+type);

            //获取属性上的注解Language
            Language annotation = field.getAnnotation(Language.class);
            //获取注解Language的值
            Language.LanguageType lang = annotation.lang();

            //获取字段的名称
            String fName = field.getName();
            //获取字段的值
            Object fValue = field.get(info);

            System.out.println("注解值为："+lang+",fName:"+fName+",fVal:"+fValue);
            if (lang.getCode() == Language.LanguageType.CN.getCode()) {
                System.out.println("it is you !!!");

            }
        }
    }
}
