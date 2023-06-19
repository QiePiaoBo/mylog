package com.mylog.platform.gateway.routemanager;

import com.mylog.platform.gateway.models.GatewayFilterDefinition;
import com.mylog.platform.gateway.models.GatewayPredicateDefinition;
import com.mylog.platform.gateway.models.GatewayRouteDefinition;
import com.mylog.platform.gateway.service.DynamicRouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan
 */
@RestController
@RequestMapping(value = "route")
public class RouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    final String connectType = "http";



    /**
     * 增加路由
     * @param gwdefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gwdefinition){
        String flag = "fail";
        try {
            RouteDefinition definition = assembleRouteDefinition(gwdefinition);
            flag = this.dynamicRouteService.add(definition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除路由
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        try {
            return this.dynamicRouteService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新路由
     * @param gwdefinition
     * @return
     */
    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gwdefinition){
        RouteDefinition definition = assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.update(definition);
    }

    /**
     * 把传递进来的参数转化成路由对象
     * @param gatewayRouteDefinition
     * @return
     */
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gatewayRouteDefinition){

        RouteDefinition definition = new RouteDefinition();
        definition.setId(gatewayRouteDefinition.getId());
        definition.setOrder(gatewayRouteDefinition.getOrder());

        // 设置断言
        List<PredicateDefinition> pdList = new ArrayList<>();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gatewayRouteDefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition: gatewayPredicateDefinitionList){
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        // 设置过滤器
        List<FilterDefinition> filters = new ArrayList<>();
        List<GatewayFilterDefinition> gatewayFilters = gatewayRouteDefinition.getFilters();
        for (GatewayFilterDefinition gfDefinition : gatewayFilters){
            FilterDefinition filter = new FilterDefinition();
            filter.setArgs(gfDefinition.getArgs());
            filter.setName(gfDefinition.getName());
            filters.add(filter);
        }
        definition.setFilters(filters);

        URI uri = null;
        if (gatewayRouteDefinition.getUri().startsWith(connectType)){
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRouteDefinition.getUri()).build().toUri();
        }
        else {
            // uri为lb://consumer-service时使用下面的方法
            uri = URI.create(gatewayRouteDefinition.getUri());
        }
        definition.setUri(uri);
        return definition;
    }
}
