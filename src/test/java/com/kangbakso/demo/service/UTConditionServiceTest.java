package com.kangbakso.demo.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class UTConditionServiceTest {
    @Autowired
    private UTService utService;

    @MockBean
    private MongoDatabase mongoDatabase;

    @Mock
    MongoCollection<Document> mockMongoCollection;

    @Mock
    FindIterable<Document> mockIterableDocument;

    @BeforeEach
    public void initMongoTest() {
        Mockito.when(mongoDatabase.getCollection("validity")).thenReturn(mockMongoCollection);
        Mockito.when(mockMongoCollection.find()).thenReturn(mockIterableDocument);
    }

    @Test
    public void getValidDocumentTest() {
        Document mockDocument = new Document()
                .append("key1", "result1")
                .append("key2", "result2");
        Mockito.when(mockIterableDocument.first()).thenReturn(mockDocument);

        assertEquals("VALID_DOCUMENT", utService.getValidDocument());
    }

    @Test
    public void getInvalidDocument1Test() {
        Document mockDocument = new Document()
                .append("key1", "result1");
        Mockito.when(mockIterableDocument.first()).thenReturn(mockDocument);

        assertNull(utService.getValidDocument());
    }

    @Test
    public void getInvalidDocument2Test() {
        Document mockDocument = new Document()
                .append("key2", "result2");
        Mockito.when(mockIterableDocument.first()).thenReturn(mockDocument);

        assertNull(utService.getValidDocument());
    }

    @Test
    public void getInvalidDocument3Test() {
        Mockito.when(mockIterableDocument.first()).thenReturn(null);

        assertNull(utService.getValidDocument());
    }
}
