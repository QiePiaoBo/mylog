package com.mylog.ds.blog.controller;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.mapper.ArticleMapper;
import com.mylog.ds.blog.service.impl.ArticleQueryService;
import com.mylog.tools.entitys.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Dylan
 * @Date : Created in 15:06 2020/10/14
 * @Description :
 * @Function :
 */
@RestController
@RequestMapping("query")
public class ArticleQueryController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleQueryController.class);

    @Autowired
    ArticleQueryService articleQueryService;

    /**
     * 查询某用户的文章的时间归档
     * @param articleDto
     * @return
     */
    @RequestMapping("time")
    public Result queryArticleTime(@RequestBody ArticleDto articleDto){

        return articleQueryService.queryArticleTime(articleDto);
    }

    /**
     * 查询某用户某天的作品
     * @param articleDto
     * @return
     */
    @RequestMapping("articleOneDay")
    public Result queryArticleByTime(@RequestBody ArticleDto articleDto){

        return articleQueryService.queryArticleByTime(articleDto);
    }

}
