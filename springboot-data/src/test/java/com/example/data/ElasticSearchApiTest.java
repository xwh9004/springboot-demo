package com.example.data;

import com.example.data.es.ElasticSearchInstance;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 9:44 on 2020/4/16
 * @version V0.1
 * @classNmae ElasticSearchApiTest
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchApiTest {

    @Autowired
    ElasticSearchInstance esInstance;

    /**
     * create an index
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");
        Map<String, Object> properties = new HashMap<>();
        properties.put("message", message);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);

        Settings.Builder builder = Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2);
        boolean response = esInstance.createIndex("twitter",mapping,builder);
        log.info("create index {}",response);
    }

    /**
     * index exist synchronous
     * @throws IOException
     */
    @Test
    public void exist_index() throws IOException {

        System.out.println(esInstance.indexExists("twitter"));
    }

    /**
     * index exist asynchronous
     * @throws IOException
     */
    @Test
    public void exist_index_Async() throws IOException {

        esInstance.indexExistsAsync("customer", new ActionListener<Boolean>() {
            @Override
            public void onResponse(Boolean aBoolean) {
                log.info(aBoolean.toString());
            }

            @Override
            public void onFailure(Exception e) {
                log.info(e.toString());
            }
        });
    }


    /**
     * get mapping
     * @throws IOException
     */
    @Test
    public void getMapping() throws IOException {

        Map<String, Object> mapping = esInstance.getMapping("twitter");
        log.info("mapping {}",mapping);
    }

    /**
     * get setting
     * @throws IOException
     */
    @Test
    public void getSetting() throws IOException{
        GetSettingsResponse response = esInstance.getSettings("twitter");
        log.info("twitter setting {}",response);
    }

    /**
     * search
     */
    @Test
    public void search() throws IOException {
        SearchResponse response = esInstance.search("movies", "title","Tom");
        log.info("hits:{},{}",response.getHits().getHits().length,response.getHits().getHits());
    }
}
