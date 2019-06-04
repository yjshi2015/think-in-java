package com.syj.demo.designPattern;

/**
 * Created by Administrator on 2019/5/31.
 */
public class Test {

    public static void main(String[] args) {
        PartnerServiceFactory factory = new PartnerServiceFactory();
        PartnerService ponta = factory.create(1);
        PartnerService assian = factory.create(2);

        System.out.println(ponta.addPoint("syj", "123"));
        System.out.println(assian.rollbackPoint("syj", "123"));

        PartnerServiceFactory1 factory1 = new PartnerServiceFactory1();
        PartnerService ponta1 = factory1.create("com.syj.demo.designPattern.PontaService");
        PartnerService assian1 = factory1.create("com.syj.demo.designPattern.AssianAirService");

        System.out.println(ponta1.addPoint("syj", "456"));
        System.out.println(assian1.addPoint("syj", "456"));

        PartnerServiceFactory2 factory2 = new PartnerServiceFactory2();
        PartnerService ponta2 = factory2.createPontaService();
        PartnerService assian2 = factory2.createAssianService();

        System.out.println(ponta2.addPoint("syj", "789"));
        System.out.println(assian2.rollbackPoint("syj", "789"));

        //通过spring容器来获取对象实例
        PartnerService ponta3 = PartnerServiceFactory3.create(1);
        System.out.println(ponta3.addPoint("syj", "abc"));
    }
}
