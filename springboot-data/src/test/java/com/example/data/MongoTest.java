package com.example.data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:24 on 2020/4/23
 * @version V0.1
 * @classNmae MongoTest
 */
public class MongoTest {


    /**
     * 来自官方示例
     */
    @Test
    public void mongoConnect(){
        ConnectionString connString=new ConnectionString(
                "mongodb://118.31.122.126:27017"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> movies = database.getCollection("movies");
        String movie ="{\n" +
                "          \"movieId\" : 2,\n" +
                "          \"title\" : \"Jumanji (1995)\",\n" +
                "          \"genres\" : \"Adventure|Children|Fantasy\"\n" +
                "        }";
        Document doc = Document.parse(movie);
        movies.insertOne(doc);

    }
}
