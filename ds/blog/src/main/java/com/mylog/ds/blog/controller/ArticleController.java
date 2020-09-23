package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.permission.permissions.AdminPermission;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.tools.utils.entity.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

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

    @AdminPermission
    @RequestMapping("update")
    public Result updateArticle(@RequestBody Article article){
        return articleService.update(article);
    }

    @AdminPermission
    @RequestMapping("delete")
    public Result deleteById(@RequestParam Integer id){
        return articleService.deleteById(id);
    }


}