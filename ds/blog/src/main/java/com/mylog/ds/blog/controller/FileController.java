package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.permission.permissions.AdminPermission;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.tools.utils.entity.Message;
import com.mylog.tools.utils.entity.Result;
import com.mylog.tools.utils.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 文件接口
 * @author Dylan
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    IFileService fileService;

    /**
     * 文件上传
     * @param articleDto
     * @return
     * @throws IOException
     */
    @AdminPermission
    @RequestMapping("upload")
    public Result uploadFile(@ModelAttribute ArticleDto articleDto) throws IOException {
        if (articleDto.getFile()==null){
            return new Result().put("status",Status.FILE_NEED.getStatus()).put("msg", Message.FILE_NEED.getMsg());
        }
        Result result = null;
        result = fileService.uploadFile(articleDto, "qiniu");
        return result;
    }

}
