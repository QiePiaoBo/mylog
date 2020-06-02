package com.mylog.common.format.Facade.Demo4;

public class GoodFacade {
    // 被委托的对象
    private ClassA a = new ClassA();
    private ClassB b = new ClassB();
    private Context c = new Context();
    //提供给外部访问的方法
    public void methodA(){
        this.a.doSomethingA();
    }
    public void methodB(){
        this.b.doSomethingB();
    }
    public void methodC(){
        this.c.complexMethod();
    }

}
