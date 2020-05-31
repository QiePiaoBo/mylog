package com.mylog.common.format.Adapter.demo1.impl;

import com.mylog.common.format.Adapter.demo1.Target;

/**
 * 目标角色实现类
 */
public class TargetImpl implements Target {

    @Override
    public void request() {
        System.out.println("if you need help, just call me !");
    }
}
