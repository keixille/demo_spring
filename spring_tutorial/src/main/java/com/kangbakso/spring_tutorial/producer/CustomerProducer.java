package com.kangbakso.spring_tutorial.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String key, String message) {
        String topic = "test-topic";

        kafkaTemplate.send(topic, key, message);
    }
}
