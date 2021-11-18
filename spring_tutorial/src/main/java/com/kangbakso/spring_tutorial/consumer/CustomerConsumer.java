package com.kangbakso.spring_tutorial.consumer;

import org.jboss.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class CustomerConsumer {
    private static final Logger logger = Logger.getLogger(CustomerConsumer.class);

    @KafkaListener(topics = "test-topic", groupId = "spring-tutorial")
    public void listenGroupSpringTutorial(String message,
                                          @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        logger.info("[Group, Topic, Key]:[spring-tutorial, test-topic, " + key + "] = " + message);
    }
}
