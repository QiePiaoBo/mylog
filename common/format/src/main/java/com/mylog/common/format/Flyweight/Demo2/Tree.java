package com.mylog.common.format.Flyweight.Demo2;

/**
 * 树节点
 */
public class Tree {
    private final String name;
    private final String data;

    public Tree(String name, String data){
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
