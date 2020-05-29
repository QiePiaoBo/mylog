package com.mylog.tools.lic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.Map;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Result {
    private Map<String,Object> map = new LinkedHashMap<>();

    public static Result success(){
        //相当于调用下面的map 然后把值存map里面 。
        Result entity = new Result();
        entity.getMap().put("status",200);
        entity.getMap().put("message","成功");
        return entity;
    }
    //用来存值，对象，集合
    public  Result put (String key,Object value){
        this.getMap().put(key,value);
        return  this;
    }
    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
