package com.mylog.common.licence.mapper;


import com.mylog.common.licence.entity.Access;
import com.mylog.tools.model.model.page.MyPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname AccessMapper
 * @Description AccessMapper
 * @Date 5/9/2022 11:20 AM
 */
@Mapper
public interface AccessMapper {

    /**
     * 查询角色列表
     * @param myPage
     * @return
     */
    List<Access> selectAccessList(@Param("myPage") MyPage myPage);
    /**
     * 获取生效的access总数量
     * @return
     */
    Long selectAccessTotal();
}