package com.mylog.ds.blog.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 输入参数
 * @author Dylan
 */
@Data
public class ArticleDto {
    /**
     * 文章主键
     */
    private Integer id;
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
     * 文章路径
     */
    private String filePath;
    /**
     * 作者id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     *  是否禁止访问(管理员)
     */
    private String isDel;
    /**
     * 是否进行展示(作者)
     */
    private String isLock;

}
