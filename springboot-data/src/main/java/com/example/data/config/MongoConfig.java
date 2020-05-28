package com.example.data.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import static java.util.Collections.singletonList;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:09 on 2020/4/23
 * @version V0.1
 * @classNmae MongoConfig
 */
//@Configuration
public class MongoConfig {
    /*
     * Use the standard Mongo driver API to create a com.mongodb.client.MongoClient instance.
     */
//     @Bean
     public  MongoClient mongoClient() {

        return MongoClients.create("mongodb://118.31.122.126:27017");
    }
//    @Bean
    public MongoClient mongoClientWithCredential(){
                 MongoClientSettings settings = MongoClientSettings.builder()
                 .credential(
                         MongoCredential
                         .createCredential("name", "db", "pwd".toCharArray()))
                 .applyToClusterSettings(setting-> {
                     setting.hosts(singletonList(new ServerAddress("118.31.122.126", 27017)));
                 })
                 .build();

         return MongoClients.create(settings);
    }

}
