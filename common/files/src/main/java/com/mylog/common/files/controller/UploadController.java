package com.mylog.common.files.controller;

import com.mylog.common.files.model.dto.FileStorageDTO;
import com.mylog.common.files.service.QiNiuService;
import com.mylog.tools.model.model.result.DataResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname QiNiuController
 * @Description QiNiuController
 * @Date 9/15/2022 5:21 PM
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Resource
    private QiNiuService qiNiuService;

    @RequestMapping("qiniu")
    public DataResult upload2Qiniu(@ModelAttribute FileStorageDTO dto){
        return qiNiuService.upload2QiNiu(dto);
    }

}
