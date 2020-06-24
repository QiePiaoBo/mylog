package com.mylog.ds.blog.config;

import com.mylog.tools.lic.session.UserContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dylan
 * @Date : Created in 17:01 2020/6/24
 * @Description : java类
 */
@Configuration
public class UserConfig {

    @Bean
    public UserContext userContext(){
        return new UserContext();
    }

}
