package com.mylog.ds.blog.service;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.tools.utils.entity.Result;

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
    Result uploadFile(ArticleDto articleDto);
}
