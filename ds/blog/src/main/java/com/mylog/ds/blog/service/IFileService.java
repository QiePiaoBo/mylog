package com.mylog.ds.blog.service;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.tools.model.model.result.DataResult;

/**
 * 文件类接口
 * @author Dylan
 */
public interface IFileService {

    /**
     * 文件上传
     * @param articleDto
     * @param uploadWhere
     * @return
     */
    DataResult uploadFile(ArticleDto articleDto, String uploadWhere);
}
