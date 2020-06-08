package com.mylog.common.format.Flyweight.Demo1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SignInfoFactory {
    //池容器
    private static Map<String,SignInfo> pool = new ConcurrentHashMap<String,SignInfo>();
    //从池中获得对象
    public static SignInfo getSignInfo(String key){
        //设置返回对象
        SignInfo result = null;
        //池中没有该对象，则建立，并放入池中
        if(!pool.containsKey(key)){
            System.out.println(key + "----建立对象，并放置到池中");
            result = new SignInfo4Pool(key);
            pool.put(key, result);
        }else{
            result = pool.get(key);
            System.out.println(key +"---直接从池中取得");
        }
        return result;
    }
}