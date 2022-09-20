package com.mylog.business.blog.controller;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.blog.entity.dto.ArticleDto;
import com.mylog.business.blog.service.impl.ArticleQueryService;
import com.mylog.tools.model.model.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan
 * @Date : Created in 15:06 2020/10/14
 * @Description :
 * @Function :
 */
@RestController
@RequestMapping("query")
public class ArticleQueryController {
    private static final MyLogger logger = MyLoggerFactory.getLogger(ArticleQueryController.class);

    @Autowired
    ArticleQueryService articleQueryService;

    /**
     * 查询某用户的文章的时间归档
     * @param articleDto
     * @return
     */
    @RequestMapping("time")
    public DataResult queryArticleTime(@RequestBody ArticleDto articleDto){

        return articleQueryService.queryArticleTime(articleDto);
    }

    /**
     * 查询某用户某天的作品
     * @param articleDto
     * @return
     */
    @RequestMapping("articleOneDay")
    public DataResult queryArticleByTime(@RequestBody ArticleDto articleDto){

        return articleQueryService.queryArticleByTime(articleDto);
    }

}
