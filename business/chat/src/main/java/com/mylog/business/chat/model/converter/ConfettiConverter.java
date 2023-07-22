package com.mylog.business.chat.model.converter;


import com.mylog.business.chat.dal.entity.ConfettiEntity;
import com.mylog.business.chat.model.ConfettiInsertModel;
import com.mylog.business.chat.model.vo.ConfettiListVO;
import com.mylog.business.chat.model.vo.ConfettiVO;
import org.springframework.beans.BeanUtils;

public class ConfettiConverter {

    /**
     * 从数据库实体类中读取属性并赋值给ConfettiVO
     * @param entity
     * @return
     */
    public static ConfettiVO getConfettiVO(ConfettiEntity entity){
        ConfettiVO confettiVO = new ConfettiListVO();
        BeanUtils.copyProperties(entity, confettiVO);
        return confettiVO;
    }

    /**
     * 从数据库实体类中读取属性并赋值给ConfettiVO
     * @param insertModel
     * @return
     */
    public static ConfettiVO getConfettiVO(ConfettiInsertModel insertModel){
        ConfettiVO confettiVO = new ConfettiVO();
        BeanUtils.copyProperties(insertModel, confettiVO);
        return confettiVO;
    }


}
