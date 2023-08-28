package com.task.task.model;

public class AirtimePaymentRequest {
    private String phoneNumber;
    private double amount;

    public AirtimePaymentRequest(String phoneNumber, double amount) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public AirtimePaymentRequest() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
