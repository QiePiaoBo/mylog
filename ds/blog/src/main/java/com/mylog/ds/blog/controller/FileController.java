package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.permission.permissions.AdminPermission;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.tools.lic.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        MultipartFile file = articleDto.getFile();
        if (file==null){
            return new Result().put("status",444).put("msg","File is null.");
        }
        return fileService.uploadFile(articleDto);
    }

}
