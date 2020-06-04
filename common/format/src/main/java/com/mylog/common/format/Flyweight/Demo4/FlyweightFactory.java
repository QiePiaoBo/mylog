package com.mylog.common.format.Flyweight.Demo4;

import java.util.HashMap;

public class FlyweightFactory {
    //定义一个池容器
    private static  HashMap<String,Flyweight> pool= new HashMap<String,Flyweight>();
    //享元工厂
    public Flyweight getFlyweight(String Extrinsic){
        //需要返回的对象
        Flyweight flyweight = null;
        //在池中没有该对象
        if(pool.containsKey(Extrinsic)){
            System.out.println("该对象已存在，获取。");
            flyweight = pool.get(Extrinsic);
        }else{
            //根据外部状态创建享元对象
            flyweight = new ConcreteFlyweight1(Extrinsic);
            //放置到池中
            System.out.println("该对象不存在，添加。");
            pool.put(Extrinsic, flyweight);
        }
        return flyweight;
    }
}