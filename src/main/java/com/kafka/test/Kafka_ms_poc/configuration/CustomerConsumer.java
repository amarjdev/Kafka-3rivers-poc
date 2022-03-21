package com.kafka.test.Kafka_ms_poc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.test.Kafka_ms_poc.bean.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerConsumer {

    @Autowired
    public PublicCustomerBalanceTopic customerBalanceTopic;
    private final Map<String,Customer> messages = new HashMap();

    @KafkaListener(topics = "Customer")
    public void listen(String message) {
        synchronized (messages) {
            //String isValid = JsonValidation.validate(message);
            ObjectMapper mapper = new ObjectMapper();
            Customer customer;
            try {
              customer = mapper.readValue(message, Customer.class);
              messages.put(customer.getAccountId(),customer);
              customerBalanceTopic.publishCustomerBalanceOnCustomerMsg(customer);
            } catch (JsonProcessingException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            
        }
    }

    public Map<String,Customer> getMessages() {
        return messages;
    }

}
