package com.mylog.common.format.Adapter.demo1.impl;

import com.mylog.common.format.Adapter.demo1.Adaptee;

/**
 * 源角色实现类
 */
public class AdaptImpl implements Adaptee {

    // 原有的业务逻辑
    @Override
    public void doSomething(){
        System.out.println(" I'm a kind of busy, leave me alone pls");
    }
}
