package com.mylog.platform.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 */
@RestController
public class FallbackConfiguration {

    @RequestMapping("/fallback")
    public String fallback(){
        return "你触发了我的fallback，我将对你进行封印缉捕，束手就擒吧";
    }

}
