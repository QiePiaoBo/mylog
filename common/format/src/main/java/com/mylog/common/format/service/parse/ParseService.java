package com.mylog.common.format.service.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mylog.common.format.service.factory.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ParseService {

    @Autowired
    JsonFactory jsonFactory;


    /**
     * 获取最终的json格式数据
     * @return
     */
    public JSONObject getEndJson() {
        return parseString2();
    }

    /**
     * 解析方法1
     * 开箱即用的json数据只需将字符串转化为json格式即可
     * @return
     */
    public JSONObject parseString1(){
        String startJson = jsonFactory.getString1();
        JSONObject dataSource = (JSONObject) JSON.parse(startJson);
        return dataSource;
    }


    /**
     * 解析方法2
     * 原始字符串需要进一步(进亿步)转化才能成为最终的结果供调用
     * @return
     */
    public JSONObject parseString2(){
        // 声明返回对象
        JSONObject result = new JSONObject();
        // 声明cardTitle
        JSONArray cardTitle = new JSONArray();
        JSONArray cardTitleRevert = new JSONArray();
        // 声明cardContent
        JSONArray cardContent = new JSONArray();
        // 声明desc1
        String desc1 = "ABC";
        // 声明desc2
        String desc2 = "DEF";
        // 总条数
        Integer totalNumber = 0;


        String startJson = jsonFactory.getString2();
        JSONObject baseData = JSON.parseObject(startJson);
        JSONArray baseArray =  (JSONArray) baseData.get("data");
        JSONObject baseFirst = (JSONObject) baseArray.get(0);

        // 从第一条获取cardTitle
        Set<String> setTitle =  baseFirst.keySet();
        for (String str : setTitle) {
            cardTitle.add(str);
        }

        // 根据cardTitle从每一条中获取数据
        for (int i = 0; i < baseArray.size(); i ++){
            JSONObject metaJsonObject = (JSONObject) baseArray.get(i);
            List<String> metaList = new ArrayList<>();
            // 反转metaContent的内容顺序
            for (int j = 0; j < cardTitle.size(); j ++){
                String key = cardTitle.get(j).toString();
                metaList.add(metaJsonObject.get(key).toString());
            }
            cardContent.add(metaList);
        }
        totalNumber = baseArray.size();
        result.put("dataName", "查询实例");
        result.put("cardTitle", cardTitle);
        result.put("desc1", desc1);
        result.put("desc2", desc2);
        result.put("cardContent", cardContent);
        result.put("totalNumber", totalNumber);
        return result;
    }
}
