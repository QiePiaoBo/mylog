package com.mylog.common.format.Flyweight.Demo4;

public class ConcreteFlyweight2 extends Flyweight{
    //接受外部状态
    public ConcreteFlyweight2(String _intrinsic, Flyweight flyweight){
        super(flyweight.Extrinsic);
        super.setIntrinsic(_intrinsic);
    }
    //根据外部状态进行逻辑处理
    @Override
    public void operate(){
        //业务逻辑
        System.out.println("外部状态是：" + this.Extrinsic);
    }
}