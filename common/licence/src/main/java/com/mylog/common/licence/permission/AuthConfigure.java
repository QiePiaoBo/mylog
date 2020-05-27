package com.mylog.common.licence.permission;

import com.mylog.common.licence.permission.Interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器注册中心
 */
@Slf4j
@RefreshScope
@Configuration
public class AuthConfigure extends WebMvcConfigurationSupport {

    @Value("${licence.interceptors}")
    private List<String> urls;
    /**
     * 注册权限校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AuthInterceptor()).addPathPatterns(urls);
    }

    @Bean
    public AuthInterceptor AuthInterceptor(){
        return new AuthInterceptor();
    }
}