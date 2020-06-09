package com.mylog.platform.plt_gateway.repositories;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.mylog.platform.plt_gateway.models.Route;
import com.mylog.platform.plt_gateway.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

/**
 * 项目启动时从数据库获取路由列表
 */
@Component
public class MysqlRouteDefinition implements RouteDefinitionRepository {

    @Autowired
    private IRouteService routeService;

    /**
     * Gateway启动的时候，会加载这个方法
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<Route> routeList = routeService.getListByEnable();
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

            if (CollectionUtils.isNotEmpty(routeList)){
                //转换成 RouteDefinition 集合后，返回
                routeDefinitions = this.toRouteList(routeList);
                return Flux.fromIterable(routeDefinitions);
            }else {
                return Flux.fromIterable(new ArrayList<RouteDefinition>());
            }
    }

    /**
     * 重写路由保存方法
     * @param route
     * @return
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        RouteDefinition routeDefinition = route.block();
        Route mysqlRoute = new Route();
        // 构建route对象并存入数据
        mysqlRoute.setRouteId(routeDefinition.getId());
        mysqlRoute.setUri(routeDefinition.getUri().toString());
        mysqlRoute.setOrder(routeDefinition.getOrder());
        mysqlRoute.setFilters(JSONArray.toJSONString(routeDefinition.getFilters()));
        mysqlRoute.setPredicates(JSONArray.toJSONString(routeDefinition.getPredicates()));
        // 默认可用
        mysqlRoute.setStatus(1);
        //
        if (routeService.save(mysqlRoute)){
            return Mono.empty();
        }else {
            return Mono.error(new Error("保存失败"));
        }
    }
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        String id = routeId.block();

        routeService.deleteById(routeId.block());
        if (routeService.removeById(id)){
            return Mono.empty();
        }
        else {
            return Mono.error(new Error("删除失败"));
        }

    }
    /**
     * 转换成 List<RouteDefinition>
     * @param listByEnable
     * @return
     */
    private List<RouteDefinition> toRouteList(List<Route> listByEnable){
        List<RouteDefinition> routeList = new ArrayList<>();
        /**
         * 循环转换：
         * 因为数据库中，Predicates 和 Filters 存储的 json字符串。所以，得先转换成 对应的 vo.
         * 然后在转换成 List<PredicateDefinition>和 List<FilterDefinition>
         */
        listByEnable.stream().forEach(gw->{
            RouteDefinition r = this.setRouteDefinition(gw);
            routeList.add(r);
        });
        return routeList;
    }
    /**
     * 转格式 route->routeDefinition
     * @param route
     * @return
     */
    public RouteDefinition setRouteDefinition(Route route){
        RouteDefinition r = new RouteDefinition();
        r.setUri(getUri(route.getUri()));
        r.setOrder(route.getOrder());
        r.setId(route.getRouteId());
        r.setPredicates(JSONArray.parseArray(route.getPredicates(), PredicateDefinition.class));
        r.setFilters(JSONArray.parseArray(route.getFilters(), FilterDefinition.class));
        return r;
    }
    /**
     * 获取 routeDefinition 的 uri
     */
    public static URI getUri(String uriStr){
        URI uri;
        if(uriStr.startsWith("http")){
            //http地址
            uri = UriComponentsBuilder.fromHttpUrl(uriStr).build().toUri();
        }else{
            //注册中心
            uri = UriComponentsBuilder.fromUriString(uriStr).build().toUri();
        }
        return uri;
    }
}
