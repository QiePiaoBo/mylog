package com.mylog.common.format.Flyweight.Demo3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TreeFactory {
    private static Map<String, Tree> map = new ConcurrentHashMap<String, Tree>();

    public Tree getTree(String name, String data){
        if (map.containsKey(name)){
            System.out.printf("Tree " + name + " 已经存在，取出 " + name);
            return map.get(name);
        }else {
            System.out.println("Tree " + name + "不存在， 创建并取出" + name);
            Tree tree = new Tree(name, data);
            map.put(name, tree);
            return tree;
        }
    }
}
