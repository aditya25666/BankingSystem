package com.banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public boolean createAccount(Account account) {

        String query = "INSERT INTO account(customer_name, pin, balance) VALUES (?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {

            pstmt.setString(1, account.getCustomerName());
            pstmt.setString(2, account.getPin());
            pstmt.setDouble(3, account.getBalance());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Account Created Successfully!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Account getAccount(int accountNo) {

        String query = "SELECT * FROM account WHERE account_no = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {

            pstmt.setInt(1, accountNo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Account account = new Account();

                account.setAccountNo(rs.getInt("account_no"));
                account.setCustomerName(rs.getString("customer_name"));
                account.setPin(rs.getString("pin"));
                account.setBalance(rs.getDouble("balance"));

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deposit(int accountNo, double amount) {

        // Check if account exists
        Account account = getAccount(accountNo);

        if (account == null) {
            System.out.println("Account Not Found!");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return false;
        }

        double newBalance = account.getBalance() + amount;

        String query = "UPDATE account SET balance = ? WHERE account_no = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {

            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountNo);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("₹" + amount + " deposited successfully.");
                System.out.println("Updated Balance: ₹" + newBalance);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean withdraw(int accountNo, double amount) {

        // Validate amount
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return false;
        }

        // Check if account exists
        Account account = getAccount(accountNo);

        if (account == null) {
            System.out.println("Account Not Found!");
            return false;
        }

        // Check sufficient balance
        if (account.getBalance() < amount) {
            System.out.println("Insufficient Balance!");
            return false;
        }

        double newBalance = account.getBalance() - amount;

        String query = "UPDATE account SET balance = ? WHERE account_no = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {

            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountNo);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("₹" + amount + " withdrawn successfully.");
                System.out.println("Remaining Balance: ₹" + newBalance);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public double checkBalance(int accountNo) {

        Account account = getAccount(accountNo);

        if (account != null) {
            return account.getBalance();
        }

        return -1;
    }

    @Override
    public boolean deleteAccount(int accountNo) {

        // Check if account exists
        Account account = getAccount(accountNo);

        if (account == null) {
            System.out.println("Account Not Found!");
            return false;
        }

        String query = "DELETE FROM account WHERE account_no = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {

            pstmt.setInt(1, accountNo);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Account Deleted Successfully!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    @Override
    public boolean login(int accountNo, String pin) {

        String query = "SELECT * FROM account WHERE account_no = ? AND pin = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(query);
        ) {

            pstmt.setInt(1, accountNo);
            pstmt.setString(2, pin);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}