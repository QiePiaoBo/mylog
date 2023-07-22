package com.mylog.business.chat.controller;


import com.mylog.business.chat.model.ConfettiInsertModel;
import com.mylog.business.chat.model.ConfettiQueryModel;
import com.mylog.business.chat.service.ConfettiService;
import com.mylog.business.chat.service.UserService;
import com.mylog.tools.model.model.result.HttpResult;
import lombok.NonNull;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("confetti")
public class ConfettiController {

    @Resource
    private UserService userService;

    @Resource
    private ConfettiService confettiService;

    /**
     * 添加或查询纸屑需要是登陆状态 第一步需要传入userName获取id
     * @param userName
     * @return
     */
    @RequestMapping(value = "get-voucher", method = RequestMethod.GET)
    public HttpResult getVoucherForUser(@Param("userName") @NonNull String userName){
        return userService.getUserId(userName);
    }

    @RequestMapping(value = "get-confetti", method = RequestMethod.POST)
    public HttpResult getConfettiForUser(@RequestBody ConfettiQueryModel queryModel){
        return confettiService.getConfettiForUser(queryModel);
    }

    @RequestMapping(value = "add-confetti", method = RequestMethod.POST)
    public HttpResult addConfettiForUser(@RequestBody ConfettiInsertModel insertModel){
        return confettiService.addConfetti(insertModel);
    }
}
