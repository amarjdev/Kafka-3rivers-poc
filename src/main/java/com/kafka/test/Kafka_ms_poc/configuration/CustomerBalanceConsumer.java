package com.kafka.test.Kafka_ms_poc.configuration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.test.Kafka_ms_poc.bean.Customer;
import com.kafka.test.Kafka_ms_poc.bean.CustomerBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerBalanceConsumer {

    private final Map<String,CustomerBalance> messages = new HashMap();

    @KafkaListener(topics = "CustomerBalance")
    public void listen(String message) {
        synchronized (messages) {
          ObjectMapper mapper = new ObjectMapper();
          CustomerBalance customerBalance;
          try {
            customerBalance = mapper.readValue(message, CustomerBalance.class);
            messages.put(customerBalance.getAccountId(),customerBalance);
          } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }

    public Map<String,CustomerBalance> getMessages() {
        return messages;
    }

}
