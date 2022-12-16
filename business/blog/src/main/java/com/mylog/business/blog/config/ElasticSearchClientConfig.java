package com.mylog.business.blog.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dylan
 * @Description ElasticSearchClientConfig
 * @Date : 2022/12/6 - 23:39
 */
@Configuration
public class ElasticSearchClientConfig {

    @Value("${elasticsearch.host:192.168.2.111}")
    private String esHost;

    @Value("${elasticsearch.port:9200}")
    private Integer port;

    @Value("${elasticsearch.protocol:http}")
    private String protocol;


    @Bean("mylogEsNode1")
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(esHost, port, protocol))
        );
        return client;
    }

}
