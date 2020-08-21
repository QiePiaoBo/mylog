package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.permission.permissions.AdminPermission;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.tools.file.filesdk.QiNiuSdk;
import com.mylog.tools.utils.entity.Result;
import com.mylog.tools.utils.utils.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

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
     * @return
     */
    @AdminPermission
    @RequestMapping("upload")
    public Result uploadFile(@ModelAttribute ArticleDto articleDto){
        File file = FileUtils.multi2File(articleDto.getFile());
        if (file==null){
            return new Result().put("status",444).put("msg","File is null.");
        }
        Response response = null;
        response = fileService.upload2QiNiu(file);
        return Result.success();
    }

}
