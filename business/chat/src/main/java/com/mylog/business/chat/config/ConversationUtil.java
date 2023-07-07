package com.mylog.business.chat.config;

/**
 * @Classname ConversationUtil
 * @Description ConversationUtil
 * @Date 7/7/2023 2:57 PM
 */
public class ConversationUtil {

    /**
     * 组装会话发起者与会话接收者字符串 作为一些映射关系的Key 注意 如果是点对点消息 两个入参是两个字符串 如果是点对群消息 第二个入参应该是数字类型字符串
     * @param startUser
     * @param talkWith
     * @return
     */
    public static String getConversationMapKey(String startUser, String talkWith){
        return startUser + "&->" + talkWith;
    }
}
