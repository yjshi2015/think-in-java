package com.syj.demo.thread._2020_rpc.rpcDemo;

/**
 * Created by Administrator on 2020/6/28.
 * @desc provider的接口实现，具体的业务逻辑
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String ping) {
        return ping != null ? ping + " --> I am ok." : "I am ok";
    }
}
