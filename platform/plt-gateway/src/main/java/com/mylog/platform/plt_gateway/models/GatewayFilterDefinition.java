package com.mylog.platform.plt_gateway.models;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器模型
 */
@Data
public class GatewayFilterDefinition {

    // 过滤器名
    private String name;

    // 对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();

}
