package com.syj.demo.annotation;

import com.alibaba.fastjson.JSONObject;

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
            throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        ActivityInfo info = new ActivityInfo();
        info.setId(1);
        info.setDscEn("this is englist");
        info.setDscCn("这是中文");
        info.setDscId("Indonisia");

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

            //获取字段类型,多语言字段类型必须为String
            Type type = field.getGenericType();
            if (!"class java.lang.String".equals(type.toString())) {
                continue;
            }

            //获取属性上的注解Language
            Language annotation = field.getAnnotation(Language.class);
            //获取注解Language的值
            Language.LanguageType lang = annotation.lang();

            //获取字段的名称
            String fName = field.getName();
            //获取字段的值
            Object fValue = field.get(info);

            if (lang.getCode() == Language.LanguageType.EN.getCode()) {
                System.out.println("语言类型为："+lang+",fName:"+fName+",fVal:"+fValue);
                //获取对应的父字段,约定多语言字段格式：xxxEn、xxxCn、xxxId,通用字段的格式为xxx,以下根据该约定获取父类字段名称
                Field superField = clazz.getSuperclass().getDeclaredField(fName.substring(0,fName.length()-2));
                superField.setAccessible(true);
                //父字段赋值
                superField.set(info, fValue);
            }
        }
        System.out.println("============" + JSONObject.toJSONString(info));
    }
}
