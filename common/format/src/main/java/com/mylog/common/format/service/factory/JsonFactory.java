package com.mylog.common.format.service.factory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonFactory {

    /**
     * 将字符串01返回
     * {
     *  "dataName":"",
     *  "cardTitle":["姓名", "手机号", "余额"],
     *  "desc1":"余额超过45000.0的客户有3人",
     *  "desc2":"欠款超过10000.0的客户有5人",
     *  "cardContent":[
     *  ["Dylan", "15900000001", "100000.0"]
     *  ["Lucifer", "15900000002", "60000.0"]
     *  ["Will", "15900000003", "30000.0"]
     *  ["Patrick", "15900000004", "-30000.0"]
     *  ],
     *  "totalNumber":"10"
     *
     * }
     * @return
     */
    public String getString1() {
        String startJson = "{\n" +
                "\t\"dataName\":\"用户信息查询\",\n" +
                "    \"cardTitle\":[\"姓名\", \"手机号\", \"余额\"],\n" +
                "    \"desc1\":\"余额超过45000.0的客户有3人\",\n" +
                "    \"desc2\":\"欠款超过10000.0的客户有5人\",\n" +
                "    \"cardContent\":[\n" +
                "       [\"Dylan\",\"15900000001\",\"60000.0\"],\n" +
                "       [\"Richel\",\"15900000002\",\"50000.0\"],\n" +
                "       [\"Will\",\"15900000003\",\"48000.0\"],\n" +
                "       [\"Patrick\",\"15900000004\",\"-10000.0\"],\n" +
                "       [\"Tom\",\"15900000004\",\"-13000.0\"],\n" +
                "       [\"Pick\",\"15900000004\",\"-15000.0\"],\n" +
                "       [\"Bill\",\"15900000004\",\"-17000.0\"],\n" +
                "       [\"Lucifer\",\"15900000004\",\"-30000.0\"]\n" +
                "    ],\n" +
                "    \"totalNumber\":10\n" +
                "}";
        return startJson;
    }

    /**
     * 返回字符串
     * {
     *     ["name":"Dylan", "phone":"15900000001", "money":"60000.0"],
     *     ["name":"Lucifer", "phone":"15900000002", "money":"50000.0"],
     *     ["name":"Onion", "phone":"15900000003", "money":"35000.0"],
     *     ["name":"Patrick", "phone":"15900000004", "money":"-50000.0"],
     *     ["name":"Duke", "phone":"15900000005", "money":"-30000.0"],
     * }
     * @return
     */
    public String getString2(){
        String startJson = "{\n" +
                "    \"data\":[\n" +
                "    {\"name\":\"Dylan\", \"phone\":\"15900000001\", \"money\":\"60000.0\"},\n" +
                "    {\"name\":\"Lucifer\", \"phone\":\"15900000002\", \"money\":\"50000.0\"},\n" +
                "    {\"name\":\"Onion\", \"phone\":\"15900000003\", \"money\":\"35000.0\"},\n" +
                "    {\"name\":\"Patrick\", \"phone\":\"15900000004\", \"money\":\"-50000.0\"},\n" +
                "    {\"name\":\"Duke\", \"phone\":\"15900000005\", \"money\":\"-30000.0\"}\n" +
                "    ]\n" +
                "}";
        return startJson;
    }

}
