package com.banking;

public class Account {

    private int accountNo;
    private String customerName;
    private String pin;
    private double balance;

    // Default Constructor
    public Account() {

    }

    // Constructor for creating a new account
    public Account(String customerName, String pin, double balance) {
        this.customerName = customerName;
        this.pin = pin;
        this.balance = balance;
    }

    // Constructor with all fields
    public Account(int accountNo, String customerName, String pin, double balance) {
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.pin = pin;
        this.balance = balance;
    }

    // Getters and Setters

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "==============================\n" +
               "      ACCOUNT DETAILS\n" +
               "==============================\n" +
               "Account Number : " + accountNo + "\n" +
               "Customer Name  : " + customerName + "\n" +
               "Balance         : ₹" + balance + "\n" +
               "==============================";
    }
}