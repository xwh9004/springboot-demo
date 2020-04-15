package com.example.data;

import com.example.data.config.PropertyConfig;
import com.example.data.entity.User;
import com.example.data.es.ElasticSearchInstance;
import com.example.data.mapper.db1.DB1_UserMapper;
import com.example.data.mapper.db2.DB2_UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
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
 * <p><b>Company:</b>
 *
 * @author created by JesseHsu at 17:35 on 2020/3/3
 * @version V0.1
 * @classNmae SpringDataApplicationTests
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataApplicationTests {

    @Autowired
    DB1_UserMapper db1_userMapper;

    @Autowired
    DB2_UserMapper db2_userMapper;

    @Autowired
    PropertyConfig propertyConfig;

    @Autowired
    ElasticSearchInstance esInstance;

    @Test
    public void db_w_Test() {
        User user = new User();
        user.setId(3);
        user.setFirstName("Jesse");
        user.setLastName("Hsu");
        db1_userMapper.insertUser(user);
    }


    @Test
    public void db_r_Test() {

        User user = db2_userMapper.queryUserById(1);
        System.out.println(user);
    }

    @Test
    public void test_get_config() {
        System.out.println(propertyConfig.getName());
        System.out.println(propertyConfig.getUrl());

    }

    @Test
    public void exist_index() throws IOException {

        System.out.println(esInstance.indexExists("customer"));
    }

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

    @Test
    public void getMapping() throws IOException {

        Map<String, Object> mapping = esInstance.getMapping("twitter");
        log.info("mapping {}",mapping);
    }

}
