package com.mylog.business.chat.dal.mapper;

import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.dal.model.MsgQueryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname MsgRecordMapper
 * @Description TODO
 * @Date 6/20/2023 5:13 PM
 */
@Mapper
public interface MsgRecordMapper {

    /**
     * 根据请求参数查询对应的消息记录
     * @param queryModel
     * @return
     */
    List<MsgRecordEntity> queryMsgRecords(MsgQueryModel queryModel);

}
