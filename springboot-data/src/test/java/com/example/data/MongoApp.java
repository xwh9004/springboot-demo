package com.example.data;

import com.example.data.entity.Person;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:58 on 2020/4/23
 * @version V0.1
 * @classNmae MongoApp
 */
@Slf4j
public class MongoApp {
    public static void main(String[] args) {
        MongoOperations mongoOps = new MongoTemplate(MongoClients.create("mongodb://118.31.122.126:27017"), "test");
        Person p = new Person("Joe", 34);
        // Insert is used to initially store the object into the database.
        mongoOps.insert(p);
        log.info("Insert: " + p);

        // Find
        p = mongoOps.findById(p.getId(), Person.class);
        log.info("Found: " + p);

        // Update
        mongoOps.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        p = mongoOps.findOne(query(where("name").is("Joe")), Person.class);
        log.info("Updated: " + p);

        // Delete
        mongoOps.remove(p);

        // Check that deletion worked
        List<Person> people =  mongoOps.findAll(Person.class);
        log.info("Number of people = : " + people.size());
        mongoOps.dropCollection(Person.class);
    }
}
