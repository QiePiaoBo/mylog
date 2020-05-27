package com.mylog.common.licence.permission;

import com.mylog.common.licence.permission.Interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器注册中心
 */
@Configuration
public class AuthConfigure extends WebMvcConfigurationSupport {

    /**
     * 注册权限校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AuthInterceptor()).addPathPatterns("/user/all","/user/delete");
    }

    @Bean
    public AuthInterceptor AuthInterceptor(){
        return new AuthInterceptor();
    }
}
