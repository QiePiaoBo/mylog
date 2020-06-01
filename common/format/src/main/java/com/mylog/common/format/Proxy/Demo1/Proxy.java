package com.mylog.common.format.Proxy.Demo1;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 代理类
 */
public class Proxy implements Subject {
    private List<Object> objectList = null;
    // 要代理哪个实现类
    private Subject subject = null;
    // 默认被代理者
    public Proxy(){
        this.subject = new RealSubject();
    }
    // 通过构造函数传递代理者们
    public Proxy(Object... objects){
        if (objects == null)
        {
            System.out.println("?????");
            return;
        }
        List<Object> list= Arrays.asList(objects);
        if (list.get(0).getClass().toString().equals("Subject")){
            this.subject = (Subject) list.get(0);
            System.out.println(this.subject);
        }
    }
    // 通过构造函数传递代理者
    public Proxy(Subject _subject){
        this.subject = _subject;
    }
    // 实现接口中定义的方法
    public void request(){
        this.before();
        this.subject.request();
        this.after();
    }
    // 预处理
    private void before() {
        System.out.println("呼叫总部呼叫总部，这里是Proxy 我开始代理了");
    }
    // 善后处理
    private void after() {
        System.out.println("呼叫总部呼叫总部，这里是Proxy 我结束代理了");
    }

}
