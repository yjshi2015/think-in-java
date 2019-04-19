package com.syj.demo.annotation;

import lombok.Data;

/**
 * Created by Administrator on 2019/4/8.
 */
@Data
public class ActivityInfo extends ActivityBaseInfo{

    @Language(lang = Language.LanguageType.EN)
    private String dscEn;

    @Language(lang = Language.LanguageType.CN)
    private String dscCn;

    @Language(lang = Language.LanguageType.ID)
    private String dscId;
}
