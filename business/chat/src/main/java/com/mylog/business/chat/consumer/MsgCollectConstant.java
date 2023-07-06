package com.mylog.business.chat.consumer;

import com.dylan.protocol.logicer.LogicerMessage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Classname MsgCollectConstant
 * @Description MsgCollectConstant
 * @Date 7/6/2023 11:02 AM
 */
public class MsgCollectConstant {

    public static LinkedBlockingQueue<LogicerMessage> MSG_COLLECT_QUEUE = new LinkedBlockingQueue<>();

}
