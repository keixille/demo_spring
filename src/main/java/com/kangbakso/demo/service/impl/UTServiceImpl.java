package com.kangbakso.demo.service.impl;

import com.kangbakso.demo.service.UTService;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UTServiceImpl implements UTService {
    @Autowired
    MongoDatabase mongoDatabase;

    public String getFoods() {
        return "this is food";
    }

    public String switching(int num) {
        String result;

        switch (num) {
            case 0:
                result = "this is 0";
                break;
            case 1:
                result = "this is 1";
                break;
            case 2:
                result = "this is 2";
                break;
            default:
                result = "this is default";
        }

        return result;
    }

    public String getFoodDocument() {
        Document doc = mongoDatabase.getCollection("foods").find().first();
        return doc.getString("food_name");
    }

    public String getValidDocument() {
        Document validDoc = mongoDatabase.getCollection("validity").find().first();

        if (validDoc != null || validDoc.containsKey("key1") || validDoc.containsKey("key2")) {
            return "VALID_DOCUMENT";
        }
        return null;
    }
}
