package com.mylog.business.chat.dal.mapper;

import com.mylog.business.chat.dal.entity.LgcTalkSessionEntity;
import com.mylog.business.chat.dal.entity.MsgRecordEntity;
import com.mylog.business.chat.model.MsgInsertModel;
import com.mylog.business.chat.model.MsgQueryModel;
import com.mylog.business.chat.model.SessionInsertModel;
import com.mylog.business.chat.model.SessionQueryModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname MsgRecordMapper
 * @Description TODO
 * @Date 6/20/2023 5:13 PM
 */
@Mapper
public interface LgcTalkSessionMapper {

    /**
     * 根据请求参数查询对应的会话
     * @param queryModel
     * @return
     */
    List<LgcTalkSessionEntity> querySessions(SessionQueryModel queryModel);

    /**
     * 批量插入会话
     * @param insertModels
     * @return
     */
    Integer batchInsertSession(List<SessionInsertModel> insertModels);


    /**
     * 添加单个会话
     * @param insertModel
     * @return
     */
    Integer insertSession(SessionInsertModel insertModel);

}
