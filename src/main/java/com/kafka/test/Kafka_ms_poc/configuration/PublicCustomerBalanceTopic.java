package com.kafka.test.Kafka_ms_poc.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.kafka.test.Kafka_ms_poc.bean.Balance;
import com.kafka.test.Kafka_ms_poc.bean.Customer;
import com.kafka.test.Kafka_ms_poc.bean.CustomerBalance;
@Configuration
public class PublicCustomerBalanceTopic {
  
  @Autowired
  private KafkaTemplate<String, String> template;
  
  private static Map<String,CustomerBalance> customerBalanceMap = new HashMap<String, CustomerBalance>();
  
  public void publishCustomerBalanceOnCustomerMsg(Customer customer) {
    if(customerBalanceMap.containsKey(customer.getAccountId())) {
      customerBalanceMap.get(customer.getAccountId()).setCustomerId(customer.getCustomerId());
      template.send("CustomerBalance",customerBalanceMap.get(customer.getAccountId()).toString());
    } else {
      CustomerBalance customerBalance = new CustomerBalance(customer.getCustomerId(),customer.getAccountId(),0, customer.getPhoneNumber());
      customerBalanceMap.put(customer.getAccountId(),customerBalance);
    }
    
  }
  public void publishCustomerBalanceOnBalanceMsg(Balance balance) {
    if(customerBalanceMap.containsKey(balance.getAccountId())) {
      customerBalanceMap.get(balance.getAccountId()).setBalance(balance.getBalance());
      template.send("CustomerBalance",customerBalanceMap.get(balance.getAccountId()).toString());
    } else {
      CustomerBalance customerBalance = new CustomerBalance(null,balance.getAccountId(),balance.getBalance(), null);
      customerBalanceMap.put(balance.getAccountId(),customerBalance);
    }
  }

}
