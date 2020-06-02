package com.mylog.common.format.Facade.Demo2;

/**
 * 新邮局类，支持信件检查
 */
public class ModenPostOffice {
    private ILetterProcess letterProcess = new LetterProcessImpl();
    // 警察
    private Police letterPolice = new Police();
    //写信，封装，投递，一体化
    public void sendLetter(String context,String address){
        //帮你写信
        letterProcess.writeContext(context);
        //写好信封
        letterProcess.fillEnvelope(address);
        // 添加检查信件
        letterPolice.checkLetter(letterProcess);
        //把信放到信封中
        letterProcess.letterInotoEnvelope();
        //邮递信件
        letterProcess.sendLetter();
    }
}