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

        ActivityInfo after = LanguageUtils.specifiedLanguage(info, Language.LanguageType.EN);
        System.out.println("============" + JSONObject.toJSONString(after));
    }
}
