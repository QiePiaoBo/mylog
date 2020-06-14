package com.mylog.ds.blog.service;

import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.tools.lic.entity.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件类接口
 * @author Dylan
 */
public interface IFileService {

    public Result uploadFile(MultipartFile file, ArticleDto articleDto);
}
