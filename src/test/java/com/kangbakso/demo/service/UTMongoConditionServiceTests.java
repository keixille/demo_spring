package com.kangbakso.demo.service;

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
public class UTMongoConditionServiceTests {
    @Autowired
    private UTService utService;

    @MockBean
    private MongoDatabase mongoDatabase;

    @Mock
    MongoCollection<Document> mockMongoCollection;

    @Mock
    FindIterable<Document> mockIterableDocument;

//    @BeforeEach
//    public void initMongoTest() {
//        Mockito.when(mongoDatabase.getCollection("validity")).thenReturn(mockMongoCollection);
//        Mockito.when(mockMongoCollection.find()).thenReturn(mockIterableDocument);
//    }

    @Test
    public String getValidDocument1Test() {
        Mockito.when(mongoDatabase.getCollection("validity")).thenReturn(mockMongoCollection);
        Mockito.when(mockMongoCollection.find()).thenReturn(mockIterableDocument);
        Document mockDocument = new Document().append("key1", "result1");
        Mockito.when(mockIterableDocument.first()).thenReturn(mockDocument);

        assertEquals("VALID_DOCUMENT", utService.getValidDocument());
        return null;
    }
}
