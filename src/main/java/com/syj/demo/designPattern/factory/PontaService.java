package com.syj.demo.designPattern.factory;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/5/31.
 */
@Service("pontaService")
public class PontaService implements PartnerService{
    @Override
    public boolean addPoint(String pin, String sn) {
        System.out.println("我是ponta，消耗了100积分");
        return false;
    }

    @Override
    public boolean rollbackPoint(String pin, String sn) {
        System.out.println("我是ponta，回滚了100积分");
        return false;
    }
}
