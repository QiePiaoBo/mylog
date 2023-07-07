package com.mylog.business.chat.model.converter;

import com.dylan.protocol.logicer.LogicerConstant;
import com.dylan.protocol.logicer.LogicerMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.UserNameIdModel;
import com.mylog.business.chat.model.vo.MsgRecordVO;
import com.mylog.tools.utils.utils.Safes;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Classname MsgRecordConverter
 * @Description MsgRecordConverter
 * @Date 6/28/2023 3:33 PM
 */
public class MsgRecordConverter {

    /**
     * entity获取VO
     * @param entity
     * @return
     */
    public static MsgRecordVO getVoForEntity(MsgRecordEntity entity){
        MsgRecordVO msgRecordVO = new MsgRecordVO();
        BeanUtils.copyProperties(entity, msgRecordVO);
        return msgRecordVO;
    }

    /**
     * 根据LogicerMessage组装MsgInsertModel
     * @param message
     * @return
     */
    public static MsgInsertModel getInsertModel(LogicerMessage message, Integer fromId, Integer toId, Integer msgAreaType){
        if (Objects.isNull(fromId) || Objects.isNull(toId)){
            return null;
        }
        // 计算sessionId
        String sessionIdStr = message.getLogicerHeader().getSessionId();
        Integer sessionId = -1;
        if (StringUtils.isNumeric(sessionIdStr)){
            sessionId = Integer.parseInt(sessionIdStr);
        }
        // 计算content
        String msgContent = "Empty";
        try {
            msgContent = LogicerConstant.COMMON_OBJECT_MAPPER.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        MsgInsertModel model = new MsgInsertModel();
        model.setSessionId(sessionId);
        model.setMsgContent(msgContent);
        model.setMsgTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(message.getLogicerContent().getActionTimeStamp()), ZoneId.systemDefault()));
        model.setFromId(fromId);
        model.setToId(toId);
        model.setMsgAreaType(msgAreaType);
        return model;
    }



}
