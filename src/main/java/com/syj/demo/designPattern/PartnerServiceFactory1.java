package com.syj.demo.designPattern;

/**
 * Created by Administrator on 2019/5/31.
 */
public class PartnerServiceFactory1 {

    static PartnerService create(String serverName) {
        try {
            return (PartnerService) Class.forName(serverName).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("unable to instantiate" + serverName);
        }
    }
}
