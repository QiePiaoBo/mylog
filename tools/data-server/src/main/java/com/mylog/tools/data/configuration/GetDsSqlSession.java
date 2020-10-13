package com.mylog.tools.data.configuration;

import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Dylan
 * @Date : Created in 14:02 2020/9/28
 * @Description :
 * @Function :
 */
public class GetDsSqlSession {
    private static final Logger logger = LoggerFactory.getLogger(GetDsSqlSession.class);

    @Autowired
    DsSqlSessionFactory dsSqlSessionFactory;

    public SqlSession getDsSqlSesion(){
        return dsSqlSessionFactory.getSqlSession();
    }
}
