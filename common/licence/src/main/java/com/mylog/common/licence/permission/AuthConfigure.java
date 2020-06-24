package com.mylog.common.licence.permission;

import com.mylog.common.licence.permission.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 拦截器注册中心
 * @author Dylan
 */
@Slf4j
@RefreshScope
@Configuration
public class AuthConfigure implements WebMvcConfigurer {

    @Value("${licence.interceptors}")
    private List<String> urls;
    /**
     * 注册权限校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns(urls);
    }

    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}
