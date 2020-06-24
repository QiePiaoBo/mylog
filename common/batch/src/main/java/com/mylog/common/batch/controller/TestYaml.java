package com.mylog.common.batch.controller;

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
    @Value("#{${ruleMap}}")
    private Map<String, String> map;

    @RequestMapping("get")
    public String getYaml(){
        System.out.println("aaaaaaaaaa");
        try {
            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }



}

