package com.mylog.ds.blog.service;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.tools.utils.entity.Result;
import com.qiniu.http.Response;

import java.io.File;

/**
 * 文件类接口
 * @author Dylan
 */
public interface IFileService {

    /**
     * 文件上传
     * @param articleDto
     * @return
     */
    Result uploadFile(ArticleDto articleDto, String uploadWhere);

    /**
     * 文件上传到七牛
     * @param file
     * @return
     */
    Response upload2QiNiu(File file);
}
