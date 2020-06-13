package com.mylog.demos.feign.service;

import com.mylog.demos.feign.rpc.GetHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dylan
 */
@Service
public class HelloService {
    @Autowired
    private GetHello getHello;

    /**
     * 执行远程接口中调来的方法
     * @return
     */
    public String sayHello(){
        return getHello.sayHello();
    }
}
