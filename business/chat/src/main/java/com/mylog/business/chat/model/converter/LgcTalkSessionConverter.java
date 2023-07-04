package com.mylog.business.chat.model.converter;

import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.model.vo.LgcTalkSessionVO;
import com.mylog.business.chat.model.vo.MsgRecordVO;
import org.springframework.beans.BeanUtils;

/**
 * @Classname MsgRecordConverter
 * @Description MsgRecordConverter
 * @Date 6/28/2023 3:33 PM
 */
public class LgcTalkSessionConverter {

    /**
     * entity获取VO
     * @param entity
     * @return
     */
    public static LgcTalkSessionVO getVoForEntity(LgcTalkSessionEntity entity){
        LgcTalkSessionVO msgRecordVO = new LgcTalkSessionVO();
        BeanUtils.copyProperties(entity, msgRecordVO);
        return msgRecordVO;
    }



}