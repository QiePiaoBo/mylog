package com.mylog.common.format.Composite.Demo3;

public class Leaf extends Component {
    /*
     * 可以覆写父类方法
     * public void doSomething(){
     *
     * }
     */
    @Override
    public void doSomething(){
        System.out.println("Leaf ...");
    }
}