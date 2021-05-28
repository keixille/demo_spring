package com.kangbakso.demo.service;

import com.kangbakso.demo.common.util.SwitchService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MongoDbServiceTests {
    @Autowired
    private MongoDbService mongoDbService;

    @MockBean
    private MongoDatabase mongoDatabase;

    @Mock
    MongoCollection<Document> mockMongoCollection;

    @Mock
    FindIterable<Document> mockIterableDocument;

    @Test
    public void mongoDbServiceTest() {
        Document mockDocument = new Document().append("food_name", "nasi goreng");
        Mockito.when(mongoDatabase.getCollection("foods")).thenReturn(mockMongoCollection);
        Mockito.when(mockMongoCollection.find()).thenReturn(mockIterableDocument);
        Mockito.when(mockIterableDocument.first()).thenReturn(mockDocument);

        assertEquals("nasi goreng", mongoDbService.getFoodDocument());
    }

}
