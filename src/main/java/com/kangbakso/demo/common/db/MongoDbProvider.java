package com.kangbakso.demo.common.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MongoDbProvider {
    @Value("${spring.data.mongo.uri}")
    private String mongoDbUri;

    @Value("${spring.data.mongo.database}")
    private String mongoDbDatabase;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoDbUri))
                .build());
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(mongoDbDatabase);
    }
}
