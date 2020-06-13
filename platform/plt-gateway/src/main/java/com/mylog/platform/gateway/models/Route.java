package com.mylog.platform.gateway.models;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 区别于GatewayRouteDefinition
 * Route是操作数据库之用
 * 将predicates和filters变成了字符串而不是ArrayList
 *
 * @author Dylan
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Route implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 路由Id
     */
    @TableField("route_id")
    private String routeId;

    /**
     * 路由规则转发的uri
     */
    @TableField("`uri`")
    private String uri;

    /**
     * 路由的执行顺序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 路由断言集合配置json串
     */
    @TableField("predicate_json")
    private String predicates;

    /**
     * 路由过滤器集合配置json串
     */
    @TableField("filter_json")
    private String filters;

    /**
     * 状态：0,"不可用")；1,"可用")
     */
    @TableField("`status`")
    private Integer status;

    public Route(String routeId, String uri, Integer order, String predicates, String filters, Integer status) {
        this.routeId = routeId;
        this.uri = uri;
        this.order = order;
        this.predicates = predicates;
        this.filters = filters;
        this.status = status;
    }

    public Route() {
    }
}