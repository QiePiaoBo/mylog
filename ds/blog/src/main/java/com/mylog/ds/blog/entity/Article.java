package com.mylog.ds.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author Dylan
 * @since 2020-06-14 20:24:19
 */
@TableName("article")
public class Article implements Serializable {
    private static final long serialVersionUID = -66199659751436277L;
    /**
    * 文章主键
    */
    @TableId(type = IdType.AUTO)
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

}