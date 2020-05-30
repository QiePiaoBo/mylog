package com.mylog.platform.plt_gateway.service;

import com.mylog.platform.plt_gateway.repositories.MysqlRouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 操作路由 非Mysql 弃
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    @Autowired
    private MysqlRouteDefinition routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
    /**
     *  增加路由
     */
    public String add(RouteDefinition definition){
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        doLoad();
        return "success";
    }

    /**
     * 动态更新路由并刷新
     * @param definition
     * @return
     */
    public String update(RouteDefinition definition) {
        try {
            delete(definition.getId());
        } catch (Exception e) {
            return "update fail,not find route  routeId: "+definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            doLoad();
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }
    }

    /**
     * 动态删除路由并刷新
     * @param id
     * @return
     */
    public String delete(String id) {
        try {
            routeDefinitionWriter.delete(Mono.just(id)).subscribe();
            this.doLoad();
        }catch (Exception e){
            e.printStackTrace();
            return "delete fail, route " + id + "not found";
        }
        return "delete success";
    }

    /**
     * 重新刷新 路由
     */
    public String doLoad() {
        try {
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        }catch (Exception e){
            e.printStackTrace();
            return "load fail";
        }
        return "load success";
    }


}
