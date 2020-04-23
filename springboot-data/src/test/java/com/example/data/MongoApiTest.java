package com.example.data;

import com.example.data.entity.Person;
import com.example.data.mongodb.MongoApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p><b>Description:</b>
 * MongoApi 测试
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:46 on 2020/4/23
 * @version V0.1
 * @classNmae MongoApiTest
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApiTest {

    @Autowired
    public MongoApi mongoApi;

    @Test
    public void insertOne(){
        mongoApi.getMongoTemplate("test").insert(new Person("Joe", 34),"person");
    }
}
