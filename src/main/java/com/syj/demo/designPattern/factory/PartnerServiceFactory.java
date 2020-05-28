package com.syj.demo.designPattern.factory;

/**
 * Created by Administrator on 2019/5/31.
 */
public class PartnerServiceFactory {

    static PartnerService create(int type) {
        switch (type) {
            case 1:
                return new PontaService();
            case 2:
                return new AssianAirService();
            default:
                throw new IllegalArgumentException("Incorrect type code value");
        }
    }
}
