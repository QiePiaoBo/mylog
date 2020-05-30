package com.mylog.platform.plt_gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylog.platform.plt_gateway.models.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RouteMapper extends BaseMapper<Route> {
}
