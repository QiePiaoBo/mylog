package com.mylog.platform.gateway.models;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 断言模型
 * @author Dylan
 */
@Data
public class GatewayPredicateDefinition extends PredicateDefinition {

    /**
     * 断言名
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
