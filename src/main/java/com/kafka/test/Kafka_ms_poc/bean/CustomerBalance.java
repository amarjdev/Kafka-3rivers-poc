package com.kafka.test.Kafka_ms_poc.bean;

public class CustomerBalance {
  private String customerId;
  private float balance;
  private String phoneNumber;
  private String accountId;
  
  public CustomerBalance(String customerId,String accoutId,float balance,String phoneNumber) {
    this.accountId =accoutId;
    this.balance =balance;
    this.customerId = customerId;
    this.phoneNumber = phoneNumber;
  }
  public String getCustomerId() {
    return customerId;
  }
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
  public float getBalance() {
    return balance;
  }
  public void setBalance(float balance) {
    this.balance = balance;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  @Override
  public String toString() {
    return "{customerId=" + customerId + ", balance=" + balance + ", phoneNumber="
        + phoneNumber + ", accountId=" + accountId + "}";
  }
  
  
}
