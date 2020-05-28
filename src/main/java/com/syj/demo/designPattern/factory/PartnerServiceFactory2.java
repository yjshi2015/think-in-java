package com.syj.demo.designPattern.factory;

/**
 * Created by Administrator on 2019/5/31.
 */
public class PartnerServiceFactory2 {

    static PartnerService createPontaService() {
        return new PontaService();
    }

    static PartnerService createAssianService() {
        return new AssianAirService();
    }
}
