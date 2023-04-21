package com.example.javafeatures.Entity;


import java.sql.Date;

public class Customer {
  private String userId;
  private String firstName;
  private String lastName;
  private String emailAddress;
  private String phoneNumber;
  private String billingAddress;
  private String shippingAddress;
  private java.sql.Date accountCreationDate;
  private java.sql.Date lastLoginDate;
  private String accountStatus;
  private String paymentInformation;
  private String orderHistory;
  private long loyaltyPoints;
  private String customerType;

  public Customer() {
  }

  public Customer(String userId, String firstName, String lastName, String emailAddress, String phoneNumber, String billingAddress, String shippingAddress, Date accountCreationDate, Date lastLoginDate, String accountStatus, String paymentInformation, String orderHistory, long loyaltyPoints, String customerType) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.phoneNumber = phoneNumber;
    this.billingAddress = billingAddress;
    this.shippingAddress = shippingAddress;
    this.accountCreationDate = accountCreationDate;
    this.lastLoginDate = lastLoginDate;
    this.accountStatus = accountStatus;
    this.paymentInformation = paymentInformation;
    this.orderHistory = orderHistory;
    this.loyaltyPoints = loyaltyPoints;
    this.customerType = customerType;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(String billingAddress) {
    this.billingAddress = billingAddress;
  }


  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }


  public java.sql.Date getAccountCreationDate() {
    return accountCreationDate;
  }

  public void setAccountCreationDate(java.sql.Date accountCreationDate) {
    this.accountCreationDate = accountCreationDate;
  }


  public java.sql.Date getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(java.sql.Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }


  public String getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
  }


  public String getPaymentInformation() {
    return paymentInformation;
  }

  public void setPaymentInformation(String paymentInformation) {
    this.paymentInformation = paymentInformation;
  }


  public String getOrderHistory() {
    return orderHistory;
  }

  public void setOrderHistory(String orderHistory) {
    this.orderHistory = orderHistory;
  }


  public long getLoyaltyPoints() {
    return loyaltyPoints;
  }

  public void setLoyaltyPoints(long loyaltyPoints) {
    this.loyaltyPoints = loyaltyPoints;
  }


  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerType) {
    this.customerType = customerType;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "userId='" + userId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", billingAddress='" + billingAddress + '\'' +
            ", shippingAddress='" + shippingAddress + '\'' +
            ", accountCreationDate=" + accountCreationDate +
            ", lastLoginDate=" + lastLoginDate +
            ", accountStatus='" + accountStatus + '\'' +
            ", paymentInformation='" + paymentInformation + '\'' +
            ", orderHistory='" + orderHistory + '\'' +
            ", loyaltyPoints=" + loyaltyPoints +
            ", customerType='" + customerType + '\'' +
            '}';
  }
}
