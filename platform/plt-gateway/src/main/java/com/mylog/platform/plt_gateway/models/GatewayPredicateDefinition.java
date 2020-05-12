package com.mylog.platform.plt_gateway.models;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 断言模型
 */
@Data
public class GatewayPredicateDefinition {

    // 断言名
    private String name;

    // 配置的断言规则
    private Map<String, String> args = new LinkedHashMap<>();

}
