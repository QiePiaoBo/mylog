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

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public HttpResult getById(@PathVariable Integer id){
        return accessService.getById(id);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public HttpResult deleteById(@PathVariable Integer id){
        return accessService.deleteById(id);
    }

    /**
     * 根据id更新
     * @param accessDTO
     * @return
     */
    @PatchMapping
    public HttpResult updateById(@RequestBody AccessDTO accessDTO){
        return accessService.updateById(accessDTO);
    }
}
