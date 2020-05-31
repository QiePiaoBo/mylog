package com.mylog.common.format.Adapter.demo1.impl;

import com.mylog.common.format.Adapter.demo1.Target;

/**
 * 适配器角色
 */
public class Adapter extends AdaptImpl implements Target {

    @Override
    public void request() {
        super.doSomething();
    }
}
