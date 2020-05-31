package com.mylog.common.format.Bridge;

import com.mylog.common.format.Bridge.Demo0.reality.IpodCorp;
import com.mylog.common.format.Bridge.Demo1.reality.*;

/**
 * 场景类
 */
public class Client {
    // 转型之前这样赚
//    public static void main(String[] args) {
//        System.out.println("房地产公司是这么赚钱的：");
//        // 先找到我的公司
//        HouseCorp houseCorp = new HouseCorp();
//        // 展示怎么赚钱
//        houseCorp.makeMoney();
//        System.out.println("\n" + "服装公司是这样赚钱的：");
//        // 找到我的服装公司
//        ClothesCorp clothesCorp = new ClothesCorp();
//        clothesCorp.makeMoney();
//    }
    // 转型之后这样赚
//    public static void main(String[] args) {
//        System.out.println("房地产公司是这么赚钱的：");
//        // 先找到我的公司
//        HouseCorp houseCorp = new HouseCorp();
//        // 展示怎么赚钱
//        houseCorp.makeMoney();
//        System.out.println("\n" + "A货公司是这样赚钱的：");
//        // 找到我的A货公司
//        IpodCorp ipodCorp = new IpodCorp();
//        ipodCorp.makeMoney();
//    }
    // 再次转型之后是这样赚钱的
//    public static void main(String[] args) {
//        House house = new House();
//        System.out.println("房地产公司是这样赚钱的：");
//        // 找到房地产公司
//        HouseCorp houseCorp = new HouseCorp(house);
//        // 展示赚钱方法
//        houseCorp.makeMoney();
//        System.out.println("\n");
//        // 都往后稍稍，我山寨公司可是什么都能生产
//        System.out.println("山寨公司的盈利方式：");
//        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Ipod());
//        shanZhaiCorp.makeMoney();
//    }
    // 再次改造之后良心发现做服装
    public static void main(String[] args) {
        House house = new House();
        System.out.println("房地产公司是这样赚钱的：");
        // 找到房地产公司
        HouseCorp houseCorp = new HouseCorp(house);
        // 展示赚钱方法
        houseCorp.makeMoney();
        System.out.println("\n");
        // 都往后稍稍，我山寨公司可是什么都能生产
        System.out.println("山寨公司的盈利方式：");
        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Clothes());
        shanZhaiCorp.makeMoney();
    }
}
