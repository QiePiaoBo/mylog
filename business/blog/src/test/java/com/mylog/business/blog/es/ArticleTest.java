package com.mylog.business.blog.es;

import com.alibaba.fastjson.JSON;
import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import com.mylog.business.blog.entity.Article;
import com.mylog.business.blog.mapper.ArticleMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Dylan
 * @Description EsTest
 * @Date : 2022/12/6 - 23:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    private static final MyLogger log = MyLoggerFactory.getLogger(ArticleTest.class);

    @Resource
    private ArticleMapper articleMapper;


    @Test
    public void testSaveSelective() {
        Article article = new Article();
        article.setIsLock(1);
        article.setFileName("测试插入FileName");
        article.setDescription("测试插入Description");
        article.setSubTitle("测试插入subTitle");
        article.setFileType("测试插入fileType");
        article.setFilePath("http://测试插入.com");
        article.setCreateTime(new Date());
        article.setUserId(1);
        int id = articleMapper.saveSelective(article);
        log.info("insert ok, id is : {}, article: {}", id, article);
    }

    @Test
    public void testQueryAll() {
        Article article = new Article();
        List<Article> articles = articleMapper.queryAll(article);
        log.info("Articles fit: {}", articles);
    }





}
