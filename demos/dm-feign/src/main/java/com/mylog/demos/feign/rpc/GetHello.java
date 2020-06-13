package com.mylog.demos.feign.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Dylan
 */
@FeignClient(value = "my-ribbon")
public interface GetHello {
    /**
     * 调用别的微服务的方法
     * @return
     */
    @RequestMapping(value = "/hello?name=",method = RequestMethod.GET)
    String sayHello();
}
