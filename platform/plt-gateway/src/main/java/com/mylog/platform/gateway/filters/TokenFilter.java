package com.mylog.platform.gateway.filters;


import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.core.io.buffer.DataBuffer;

import javax.xml.crypto.Data;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName TokenFilter
 * @Desc   请求认证过滤器
 * @Date 2019/6/29 17:49
 * @Version 1.0
 * @author Dylan
 */
@Slf4j
@Configuration
public class TokenFilter implements Ordered, GlobalFilter {


    final String filterPath = "dl";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 响应对象
        ServerHttpResponse response = exchange.getResponse();

//         只有综合路由才添加这个全局过滤器（routesId：route_all）
//         如果请求路径中不存在dl在这里就返回不再向下执行
        if(!request.getURI().toString().contains(filterPath)){
            log.info("filter -> return");
            // 直接跳出
            return chain.filter(exchange);
        }

//         从请求中获取 token 参数
        String token = request.getQueryParams().getFirst("token");
        if (!request.getPath().toString().contains("act")){
            // 如果为空，那么将返回 401
            if (token == null || token.isEmpty()) {

                // 响应消息内容对象
                JSONObject message = new JSONObject();
                // 响应状态
                message.put("code", -1);
                // 响应内容
                message.put("msg", "缺少凭证");
                // 转换响应消息内容对象为字节
                byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                // 设置响应对象状态码 401
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                // 返回响应对象
                return response.writeWith(Mono.just(buffer));
            }
        }
        // 获取请求地址
        String beforePath = request.getPath().pathWithinApplication().value();
        // 获取响应状态码
        HttpStatus beforeStatusCode = response.getStatusCode();
        log.info("响应码：" + beforeStatusCode + "，请求路径：" + beforePath);
        // 请求前
        log.info("filter -> before");
        // 如果不为空，就通过
        return Mono.fromRunnable(() -> {
            // 获取请求地址
            String afterPath = request.getPath().pathWithinApplication().value();
            // 获取响应状态码
            HttpStatus afterStatusCode = response.getStatusCode();
            log.info("响应码：" + afterStatusCode + "，请求路径：" + afterPath);
            // 响应后
            log.info("filter -> after");
        });
    }


    @Override
    public int getOrder() {
        return 0;
    }
}