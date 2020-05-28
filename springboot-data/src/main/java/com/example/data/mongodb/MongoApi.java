package com.example.data.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:16 on 2020/4/23
 * @version V0.1
 * @classNmae MongoApi
 */
//@Component
public class MongoApi {
    @Autowired
    public MongoClient client;


    public MongoTemplate getMongoTemplate(String database){
       return new MongoTemplate(client, database);

    }


}
