package com.mylog.ds.blog.service.impl;

import com.mylog.ds.blog.entity.Article;
import com.mylog.ds.blog.entity.dto.ArticleDto;
import com.mylog.ds.blog.mapper.ArticleMapper;
import com.mylog.entitys.entitys.entity.Message;
import com.mylog.entitys.entitys.entity.Result;
import com.mylog.entitys.entitys.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Dylan
 * @Date : Created in 16:42 2020/10/14
 * @Description :
 * @Function :
 */
@Service
public class ArticleQueryService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleQueryService.class);

    @Autowired
    ArticleMapper articleMapper;

    public Result queryArticleTime(@RequestBody ArticleDto articleDto){
        Result result;
        Set<String> countSet = new HashSet<>();
        List<Date> queryResult;
        int authorId = articleDto.getAuthorId();
        queryResult = articleMapper.queryWithCreateTime(authorId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : queryResult){
            String dateString = formatter.format(date);
            countSet.add(dateString);
        }
        List<String> countResult = new ArrayList<>(countSet);
        result = new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg())
                .data(countResult)
                .build();
        return result;
    }
    public Result queryArticleByTime(@RequestBody ArticleDto articleDto){
        Result result;
        if ("0".equals(String.valueOf(articleDto.getAuthorId())) || null == articleDto.getCreateTime()){
            result = new Result.Builder(Status.PARAM_NEED.getStatus(), Message.PARAM_NEED.getMsg())
                    .build();
            return result;
        }
        List<Article> queryResult = articleMapper.queryArticlesInOneDay(articleDto.getCreateTime(), articleDto.getAuthorId());
        result = new Result.Builder(Status.SUCCESS.getStatus(), Message.SUCCESS.getMsg())
                .data(queryResult)
                .build();
        return result;
    }

}
