package com.mylog.business.blog.service;

import com.mylog.business.blog.entity.dto.ArticleDto;
import com.mylog.tools.model.model.result.DataResult;

import java.io.IOException;

/**
 * 文件类接口
 * @author Dylan
 */
public interface ArticleFileService {

    /**
     * 文件上传
     * @param articleDto
     * @param uploadWhere
     * @return
     */
    DataResult uploadFile(ArticleDto articleDto, String uploadWhere);

}
