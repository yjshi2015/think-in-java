package com.syj.demo.designPattern;

/**
 * Created by Administrator on 2019/5/31.
 */
public interface PartnerService {

    /**
     * 增加积分
     * @param pin
     * @return
     */
    boolean addPoint(String pin, String sn);

    /**
     * 回滚积分
     * @param pin
     * @param sn
     * @return
     */
    boolean rollbackPoint(String pin, String sn);
}
