package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.service.ArticleService;
import com.mylog.tools.lic.entity.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @RequestMapping("selectArticle")
    public Article selectOne(@RequestParam Integer id) {
        return this.articleService.queryById(id);
    }

    @RequestMapping("allArticles")
    public Result getArticles(@RequestBody Article article){
        return this.articleService.queryRight(article);
    }

}