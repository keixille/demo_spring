package com.kangbakso.demo.service.impl;

import com.kangbakso.demo.service.MongoDbService;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoDbServiceImpl implements MongoDbService {
    @Autowired
    MongoDatabase mongoDatabase;

    public String getFoodDocument() {
        Document doc = mongoDatabase.getCollection("foods").find().first();
        return doc.getString("food_name");
    }
}
