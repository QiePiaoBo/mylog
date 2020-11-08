package com.mylog.ds.blog.permission;

import com.mylog.tools.utils.interceptor.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注册中心
 * @author Dylan
 */
@Slf4j
@RefreshScope
@Configuration
public class AuthConfigure implements WebMvcConfigurer {

    final String urls = "/**";
    /**
     * 注册权限校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns(urls).excludePathPatterns("/file/**");
    }

    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }


}
