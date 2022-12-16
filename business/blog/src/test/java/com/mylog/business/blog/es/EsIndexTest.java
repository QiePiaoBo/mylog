package com.mylog.business.blog.es;

import com.dylan.logger.MyLogger;
import com.dylan.logger.MyLoggerFactory;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
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
public class EsIndexTest {

    private static final MyLogger log = MyLoggerFactory.getLogger(EsIndexTest.class);

    @Autowired
    @Qualifier("mylogEsNode1")
    private RestHighLevelClient mylogEsNode1;

    private final String indexName = "mylog_test_index";

    @Test
    public void testCreateIndex() throws Exception {

        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse createIndexResponse = mylogEsNode1.indices().create(request, RequestOptions.DEFAULT);
        log.info("res: {}", createIndexResponse.index());
    }

    @Test
    public void testExistIndex() throws Exception {
        GetIndexRequest request = new GetIndexRequest(indexName);
        IndicesClient indices = mylogEsNode1.indices();
        GetIndexResponse info = indices.get(request, RequestOptions.DEFAULT);
        log.info("response: mappings---{}, settings---{}", info.getMappings(), info.getSettings());
        boolean exists = indices.exists(request, RequestOptions.DEFAULT);
        log.info("ieExists: {}", exists);
    }


    @Test
    public void testExistIndex1() throws Exception {
        GetIndexRequest request = new GetIndexRequest("user");

        IndicesClient indices = mylogEsNode1.indices();
        GetIndexResponse info = indices.get(request, RequestOptions.DEFAULT);
        log.info("response: mappings---{}, settings---{}", info.getMappings(), info.getSettings());
        boolean exists = indices.exists(request, RequestOptions.DEFAULT);
        log.info("ieExists: {}", exists);
    }





}
