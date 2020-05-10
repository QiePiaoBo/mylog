package com.mylog.platform.plt_gateway.KeyResolvers;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class UriKeyResolver implements KeyResolver {
    /*
     * @ClassName UriKeyResolver
     * @Desc TODO   根据请求的 uri 限流
     * @Date 2019/6/29 17:25
     * @Version 1.0
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getURI().getPath());
    }
}
