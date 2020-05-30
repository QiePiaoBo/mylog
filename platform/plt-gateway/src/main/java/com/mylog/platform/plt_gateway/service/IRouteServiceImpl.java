package com.mylog.platform.plt_gateway.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylog.platform.plt_gateway.mapper.RouteMapper;
import com.mylog.platform.plt_gateway.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IRouteServiceImpl extends ServiceImpl<RouteMapper, Route>
        implements IRouteService {

    @Autowired
    private RouteMapper routeMapper;

    /**
     * 从数据库获取所有可用路由
     * @param
     * @return
     */
    @Override
    public List<Route> getListByEnable() {
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<Route> routeList = routeMapper.selectList(queryWrapper);
        return routeList;
    }

    /**
     * 禁用
     * @param routeId
     * @return
     */
    @Override
    public String disabled(String routeId) {
        return "Disable" + updateEnable(routeId, 0);
    }

    /**
     * 启用
     * @param routeId
     * @return
     */
    @Override
    public String enable(String routeId) {
        return "Enable" + updateEnable(routeId, 1);
    }

    /**
     * 修改 禁用/启用 状态
     * @param routeId
     * @param status
     * @return
     */
    @Override
    public String updateEnable(String routeId, Integer status) {
        UpdateWrapper<Route> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("route_id", routeId);
        Route route = new Route();
        route.setStatus(status);
        int update = routeMapper.update(route, updateWrapper);
        if (update > 0){
            return " success.";
        }
        else {
            return " failed.";
        }
    }

    @Override
    public String update(Route route) {
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("route_id", route.getRouteId());
        int update = routeMapper.update(route, queryWrapper);
        if (update > 0){
            return "Update Success";
        }
        else {
            return "Update failed";
        }
    }

    @Override
    public String saveOne(Route route) {
        int insert =  routeMapper.insert(route);
        if (insert > 0){
            return "Insert success";
        }
        else {
            return "Insert failed";
        }
    }
}
