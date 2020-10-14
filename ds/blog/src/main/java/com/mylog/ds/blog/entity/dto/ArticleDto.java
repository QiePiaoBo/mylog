package com.mylog.ds.blog.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 输入参数
 * @author Dylan
 */
@Data
public class ArticleDto {
    /**
     * 文件本体
     */
    private MultipartFile file;
    /**
     * 文章名
     */
    private String fileName;
    /**
     * 文章二级标题
     */
    private String subTitle;
    /**
     * 文章内容梗概
     */
    private String description;
    /**
     * 文章类型
     */
    private String fileType;
    /**
     * 是否进行展示(作者)
     */
    private String isLock;

    private String sendPlace;

}
