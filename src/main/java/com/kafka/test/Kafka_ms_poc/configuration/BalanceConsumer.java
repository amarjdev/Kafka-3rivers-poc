package com.kafka.test.Kafka_ms_poc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.test.Kafka_ms_poc.bean.Balance;
import com.kafka.test.Kafka_ms_poc.bean.Customer;

import java.util.HashMap;
import java.util.Map;

@Component
public class BalanceConsumer {

    @Autowired
    public PublicCustomerBalanceTopic customerBalanceTopic;
    
    private final Map<String,Balance> messages = new HashMap();

    @KafkaListener(topics = "Balance")
    public void listen(String message) {
        synchronized (messages) {
          ObjectMapper mapper = new ObjectMapper();
          Balance balance;
          try {
            balance = mapper.readValue(message, Balance.class);
            messages.put(balance.getAccountId(),balance);
            customerBalanceTopic.publishCustomerBalanceOnBalanceMsg(balance);
          } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
    }

    public Map<String,Balance> getMessages() {
        return messages;
    }

}
