package com.mylog.business.chat.dal.mapper;

import com.mylog.business.chat.model.ConfettiInsertModel;
import org.apache.ibatis.annotations.Mapper;

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

}
