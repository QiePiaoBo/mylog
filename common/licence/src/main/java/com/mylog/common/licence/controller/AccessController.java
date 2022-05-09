package com.mylog.common.licence.controller;

import com.mylog.common.licence.service.IAccessService;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Dylan
 * @Description AccessController
 * @Date : 2022/5/9 - 23:52
 */
@RestController
@RequestMapping("access")
public class AccessController {

    @Resource
    private IAccessService accessService;

    @GetMapping
    public HttpResult getPagedAccess(@Param("page") Integer page, @Param("limit") Integer limit){
        MyPage myPage = new MyPage(page, limit);
        return accessService.getPagedAccess(myPage);
    }

}
