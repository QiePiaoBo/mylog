package com.mylog.business.blog.es;

import com.alibaba.fastjson.JSON;
import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Dylan
 * @Description EsTest
 * @Date : 2022/12/6 - 23:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDocTest {

    private static final MyLogger log = MyLoggerFactory.getLogger(EsDocTest.class);

    @Autowired
    @Qualifier("mylogEsNode1")
    private RestHighLevelClient mylogEsNode1;

    private final String indexName = "user";

    @Test
    public void testCreateDoc() throws Exception {

        UserEsEntity userEsEntity = new UserEsEntity(1003L, "Duan", "man", "25", "1171357812@qq.com");
        IndexRequest request = new IndexRequest(indexName);
        request.id("1003");
        request.timeout(TimeValue.timeValueMillis(1000));
        request.source(JSON.toJSONString(userEsEntity), XContentType.JSON);
        IndexResponse addResult = mylogEsNode1.index(request, RequestOptions.DEFAULT);

        log.info("add result status : {}", addResult.status());
        log.info("add result : {}", addResult);
    }








}
