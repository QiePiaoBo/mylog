package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.ds.blog.service.IFileService;
import com.mylog.entitys.annos.AdminPermission;
import com.mylog.entitys.entitys.entity.Message;
import com.mylog.entitys.entitys.entity.Result;
import com.mylog.entitys.entitys.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (Article)表控制层
 *
 * @author Dylan
 * @since 2020-06-14 20:24:19
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    /**
     * 文章管理服务
     */
    @Resource
    private ArticleService articleService;
    /**
     * 文件上传服务
     */
    @Autowired
    IFileService fileService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("select")
    public Article selectOne(@RequestParam Integer id) {
        return this.articleService.queryById(id);
    }

    /**
     * 获取所有的文章数据
     * @param article
     * @return
     */
    @RequestMapping("all")
    public Result getArticles(@RequestBody Article article){
        return this.articleService.queryRight(article);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @AdminPermission
    @RequestMapping("update")
    public Result updateArticle(@RequestBody Article article){
        return articleService.update(article);
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @AdminPermission
    @RequestMapping("delete")
    public Result deleteById(@RequestParam Integer id){
        return articleService.deleteById(id);
    }


    /**
     * 文件上传
     * @param articleDto
     * @return
     * @throws IOException
     */
    @AdminPermission
    @RequestMapping("upload")
    public Result uploadFile(@ModelAttribute ArticleDto articleDto) throws IOException {
        if (articleDto.getFile()==null){
            return new Result.Builder(Status.FILE_NEED.getStatus(), Message.FILE_NEED.getMsg()).build();
        }
        Result result = null;
        if (null == articleDto.getSendPlace() || "qiniu".equals(articleDto.getSendPlace())){
            result = fileService.uploadFile(articleDto, "qiniu");
        }else {
            result = fileService.uploadFile(articleDto, articleDto.getSendPlace());
        }
        return result;
    }
}