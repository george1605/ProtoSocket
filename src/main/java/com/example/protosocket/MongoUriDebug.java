package com.example.protosocket;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MongoUriDebug {

    public MongoUriDebug(Environment env) {
        System.out.println(">>> Mongo URI = " +
                env.getProperty("spring.data.mongodb.uri"));
    }
}
