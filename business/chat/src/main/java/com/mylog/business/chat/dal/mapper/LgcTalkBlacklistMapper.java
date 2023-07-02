package com.mylog.business.chat.dal.mapper;

import com.mylog.business.chat.model.BlacklistInsertModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname MsgRecordMapper
 * @Description TODO
 * @Date 6/20/2023 5:13 PM
 */
@Mapper
public interface LgcTalkBlacklistMapper {

    /**
     * 添加黑名单
     * @param insertModel
     * @return
     */
    Integer addBlacklist(BlacklistInsertModel insertModel);

}
