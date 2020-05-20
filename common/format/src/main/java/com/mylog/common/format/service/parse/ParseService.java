package com.mylog.common.format.service.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mylog.common.format.service.factory.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParseService {

    @Autowired
    JsonFactory jsonFactory;


    /**
     * 将最终的字符串解析为可用来生成excel的json
     * @return
     */
    public JSONObject getEndJson() {
        String startJson = jsonFactory.getString1();
        JSONObject dataSource = (JSONObject) JSON.parse(startJson);
        return dataSource;
    }


}
