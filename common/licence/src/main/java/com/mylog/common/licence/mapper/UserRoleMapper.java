package com.mylog.common.licence.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dylan
 * @Description UserRoleMapper
 * @Date : 2022/5/10 - 23:04
 */
@Mapper
public interface UserRoleMapper {

    List<Integer> getRoleList4User(@Param("userId") Integer userId);

}
