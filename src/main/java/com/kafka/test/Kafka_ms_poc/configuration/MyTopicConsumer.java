package com.kafka.test.Kafka_ms_poc.configuration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MyTopicConsumer {

    private final Map<String,String> messages = new HashMap();

    @KafkaListener(topics = "kafka-ms-poc")
    public void listen(String message) {
        synchronized (messages) {
            String isValid = JsonValidation.validate(message);
            messages.put(message,isValid);
        }
    }

    public Map<String,String> getMessages() {
        return messages;
    }

}
