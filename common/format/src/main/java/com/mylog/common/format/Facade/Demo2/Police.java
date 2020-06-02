package com.mylog.common.format.Facade.Demo2;

/**
 * 警察，可以检查信件
 */
public class Police {
    // 检查信件
    public void checkLetter(ILetterProcess letterProcess){
        System.out.println(letterProcess + "已经检查过了。。。");
    }
}
