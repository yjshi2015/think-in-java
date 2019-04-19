package com.syj.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/4/8.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Language {

    LanguageType DEFAULT_LANG = LanguageType.EN;

    enum LanguageType {
        EN(1, "english"),
        CN(2, "chinesc"),
        ID(3, "indonisa");

        private int code;
        private String langDsc;

        LanguageType(int code, String langDsc) {
            this.code = code;
            this.langDsc = langDsc;
        }

        public int getCode() {
            return code;
        }
    }

//    todo why not ok?
//    LanguageType lang() default DEFAULT_LANG;
    LanguageType lang() default LanguageType.EN;
}
