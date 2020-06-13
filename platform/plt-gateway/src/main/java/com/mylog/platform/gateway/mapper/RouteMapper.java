package com.mylog.platform.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.platform.gateway.models.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Dylan
 */
@Mapper
@Component
public interface RouteMapper extends BaseMapper<Route> {
}
