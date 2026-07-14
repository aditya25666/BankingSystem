package com.banking;

public interface AccountDAO {

    // Create a new account
    boolean createAccount(Account account);

    // Find account by account number
    Account getAccount(int accountNo);

    // Deposit money
    boolean deposit(int accountNo, double amount);

    // Withdraw money
    boolean withdraw(int accountNo, double amount);

    // Check account balance
    double checkBalance(int accountNo);

    // Delete an account
    boolean deleteAccount(int accountNo);
    
    boolean login(int accountNo, String pin);
}