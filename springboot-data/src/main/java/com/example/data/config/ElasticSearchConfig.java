package com.example.data.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:28 on 2020/4/14
 * @version V0.1
 * @classNmae ElasticSearchConfig
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("118.31.122.126:9200")    //此种格式设置hostAndPort 在java 14中会出错
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
