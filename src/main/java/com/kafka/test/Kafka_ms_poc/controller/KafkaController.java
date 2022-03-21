package com.kafka.test.Kafka_ms_poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.test.Kafka_ms_poc.bean.Balance;
import com.kafka.test.Kafka_ms_poc.bean.Customer;
import com.kafka.test.Kafka_ms_poc.bean.CustomerBalance;
import com.kafka.test.Kafka_ms_poc.configuration.BalanceConsumer;
import com.kafka.test.Kafka_ms_poc.configuration.CustomerBalanceConsumer;
import com.kafka.test.Kafka_ms_poc.configuration.CustomerConsumer;
import com.kafka.test.Kafka_ms_poc.configuration.MyTopicConsumer;

import java.util.Map;

@RestController
public class KafkaController {

    private KafkaTemplate<String, String> template;
    @Autowired
    private CustomerConsumer customerConsumer;
    @Autowired
    private BalanceConsumer balanceConsumer;
    @Autowired
    private CustomerBalanceConsumer customerBalanceConsumer;

    public KafkaController(KafkaTemplate<String, String> template, MyTopicConsumer myTopicConsumer) {
        this.template = template;
    }
    

    @PostMapping("/kafka/produce")
    public void produce(@RequestBody String message) {
        template.send("kafka-ms-poc", message);
    }

    @GetMapping("/kafka/customerMessages")
    public Map<String,Customer> getCustomerMessages() {
        return customerConsumer.getMessages();
    }
    
    @GetMapping("/kafka/balanceMessages")
    public Map<String,Balance> getBalanceMessages() {
        return balanceConsumer.getMessages();
    }
    
    @GetMapping("/kafka/customerBalanceMessages")
    public Map<String,CustomerBalance> getCustomerBalanceMessages() {
        return customerBalanceConsumer.getMessages();
    }
    
    @GetMapping("/kafka/test")
    public String getTest() {
        return "Authenticated";
    }

}
