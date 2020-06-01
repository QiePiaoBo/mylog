package com.mylog.common.format.Bridge.Demo2;

/**
 * 抽象化角色实现
 */
public class RefineAbstraction extends Abstraction{

    // 覆写构造函数
    public RefineAbstraction(Implementor impl) {
        super(impl);
    }

    /**
     * 修正父类的行为
     */
    @Override
    public void request() {
//        super.request();
        System.out.println("不对，修正：");
        super.getImpl().doAnything();
    }
}
