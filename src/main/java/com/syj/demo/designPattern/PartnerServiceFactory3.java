package com.syj.demo.designPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/5/31.
 */
public class PartnerServiceFactory3 {

    static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    static PartnerService create(int type) {
        switch (type) {
            case 1:
                return (PartnerService) ctx.getBean("pontaService");
            case 2:
                return (PartnerService) ctx.getBean("assianAirService");
            default:
                throw new IllegalArgumentException("Incorrect type code value");
        }
    }
}
