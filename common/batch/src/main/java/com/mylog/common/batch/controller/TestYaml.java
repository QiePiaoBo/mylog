package com.mylog.common.batch.controller;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Dylan
 * @Date : Created in 13:55 2020/6/24
 * @Description : 实验yaml取map数据
 */
@RestController
@RequestMapping("yaml")
public class TestYaml {

    private final MyLogger logger = MyLoggerFactory.getLogger(TestYaml.class);

    @Value("#{${ruleMap}}")
    private Map<String, String> map;

    @Value("${datasource.mysql.url}")
    private String url;

    @RequestMapping("get")
    public String getYaml(){
        System.out.println("aaaaaaaaaa");
        try {
            logger.error(String.valueOf(map));
            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }
    @RequestMapping("check")
    public String checkYaml(){
        try {
            logger.error(url);
            System.out.println(url);
        }catch (Exception e){
            e.printStackTrace();
        }
//        StandardServletEnvironment
        return url;
    }


}

