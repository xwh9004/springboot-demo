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


    @Test
    public void db_w_Test() {
//        User user = new User();
//        user.setId(4);
//        user.setFirstName("Jesse");
//        user.setLastName("Hsu");
//        db1_userMapper.insertUser(user);
        User find = db1_userMapper.queryUserById(4);
        System.out.println(find);
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



}
