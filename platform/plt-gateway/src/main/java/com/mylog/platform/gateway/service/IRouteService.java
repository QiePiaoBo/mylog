package com.mylog.platform.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mylog.platform.gateway.models.Route;

import java.util.List;

/**
 * @author Dylan
 */
public interface IRouteService extends IService<Route> {

    /**
     * 获取所有 启用的 路由配置信息
     * @param
     * @return
     */
    List<Route> getListByEnable();

    /**
     * 禁用指定 路由Id
     * @param routeId
     * @return
     */
    String disabled(String routeId);

    /**
     * 启用指定路由Id
     * @param routeId
     * @return
     */
    String enable(String routeId);

    /**
     * 设置 指定路由Id的 状态
     * @param routeId
     * @param status
     * @return
     */
    String updateEnable(String routeId, Integer status);

    /**
     * 更新指定的路由配置
     * @param route
     * @return
     */
    String update(Route route);

    /**
     * 新增路由配置
     * @param route
     * @return
     */
    String saveOne(Route route);


    /**
     * 删除路由配置
     * @param routeId
     * @return
     */
    String deleteById(String routeId);
}