package com.dylan.mylog.feign.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "my-ribbon")
public interface GetHello {
    @RequestMapping(value = "/hello?name=",method = RequestMethod.GET)
    String sayHello();
}
