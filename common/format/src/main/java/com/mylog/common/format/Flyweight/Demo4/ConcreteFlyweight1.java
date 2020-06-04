package com.mylog.common.format.Flyweight.Demo4;

/**
 * 具体享元角色
 */
public class ConcreteFlyweight1 extends Flyweight{
    //接受外部状态
    public ConcreteFlyweight1(String _Extrinsic){
        super(_Extrinsic);
    }
    //根据外部状态进行逻辑处理
    public void operate(){
        //业务逻辑
        System.out.println("外部状态是："+ this.Extrinsic);
    }
}
