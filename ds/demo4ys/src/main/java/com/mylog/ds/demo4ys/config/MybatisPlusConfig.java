package com.mylog.ds.demo4ys.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @author Dylan
 * @Date : 2021/7/29 - 20:34
 * @Description :
 * @Function :
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        Properties property = new Properties();
        property.setProperty("format", "true");
        paginationInterceptor.setProperties(property);
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }
}
