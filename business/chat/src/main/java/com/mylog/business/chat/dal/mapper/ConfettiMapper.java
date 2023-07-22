package com.mylog.business.chat.dal.mapper;

import com.mylog.business.chat.dal.entity.ConfettiEntity;
import com.mylog.business.chat.model.ConfettiInsertModel;
import com.mylog.business.chat.model.ConfettiQueryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname MsgRecordMapper
 * @Description TODO
 * @Date 6/20/2023 5:13 PM
 */
@Mapper
public interface ConfettiMapper {

    /**
     * 添加纸屑
     * @param insertModel
     * @return
     */
    Integer addConfetti(ConfettiInsertModel insertModel);

    /**
     * 获取某个用户Id下的纸屑
     * @param queryModel
     * @return
     */
    List<ConfettiEntity> getConfettiForUser(@Param("queryModel")ConfettiQueryModel queryModel);
}
