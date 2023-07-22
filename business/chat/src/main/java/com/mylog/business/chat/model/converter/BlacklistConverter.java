package com.mylog.business.chat.model.converter;

import com.mylog.business.chat.dal.entity.BlacklistEntity;
import com.mylog.business.chat.model.vo.LgcTalkBlacklistVO;
import org.springframework.beans.BeanUtils;

/**
 * @Classname MsgRecordConverter
 * @Description MsgRecordConverter
 * @Date 6/28/2023 3:33 PM
 */
public class BlacklistConverter {

    /**
     * entity获取VO
     * @param entity
     * @return
     */
    public static LgcTalkBlacklistVO getVoForEntity(BlacklistEntity entity){
        LgcTalkBlacklistVO blacklistVO = new LgcTalkBlacklistVO();
        BeanUtils.copyProperties(entity, blacklistVO);
        return blacklistVO;
    }



}
