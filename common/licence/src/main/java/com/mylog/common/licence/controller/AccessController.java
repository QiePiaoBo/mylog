package com.mylog.common.licence.controller;

import com.mylog.common.licence.model.dto.AccessDTO;
import com.mylog.common.licence.service.IAccessService;
import com.mylog.tools.model.model.page.MyPage;
import com.mylog.tools.model.model.result.HttpResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页获取access
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    public HttpResult getPagedAccess(@Param("page") Integer page, @Param("limit") Integer limit){
        MyPage myPage = new MyPage(page, limit);
        return accessService.getPagedAccess(myPage);
    }

    @PostMapping
    public HttpResult createAccess(@RequestBody AccessDTO accessDTO){
        return accessService.createAccess(accessDTO);
    }

    @GetMapping("/{id:\\d+}")
    public HttpResult getById(@PathVariable Integer id){
        return accessService.getById(id);
    }

    @DeleteMapping("/{id:\\d+}")
    public HttpResult deleteById(@PathVariable Integer id){
        return accessService.deleteById(id);
    }

    @PatchMapping
    public HttpResult updateById(@RequestBody AccessDTO accessDTO){
        return accessService.updateById(accessDTO);
    }
}
