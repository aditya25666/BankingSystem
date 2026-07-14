package com.banking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    	Scanner sc = new Scanner(System.in);
    	AccountDAO dao = new AccountDAOImpl();

    	int choice;

    	do {

    	    System.out.println("\n=================================");
    	    System.out.println("     BANKING MANAGEMENT SYSTEM");
    	    System.out.println("=================================");
    	    System.out.println("1. Create Account");
    	    System.out.println("2. Login");
    	    System.out.println("3. Exit");
    	    System.out.println("=================================");

    	    System.out.print("Enter your choice: ");
    	    choice = sc.nextInt();
    	    sc.nextLine();

    	    switch (choice) {

    	    case 1:

    	        System.out.print("Enter Customer Name: ");
    	        String name = sc.nextLine();

    	        System.out.print("Enter PIN: ");
    	        String pin = sc.nextLine();

    	        System.out.print("Enter Initial Balance: ");
    	        double balance = sc.nextDouble();

    	        Account account = new Account(name, pin, balance);

    	        dao.createAccount(account);

    	        break;

    	    case 2:

    	        System.out.print("Enter Account Number: ");
    	        int accountNo = sc.nextInt();

    	        System.out.print("Enter PIN: ");
    	        String loginPin = sc.next();

    	        boolean loggedIn = dao.login(accountNo, loginPin);

    	        if (loggedIn) {

    	            System.out.println("\nLogin Successful!");

    	            int customerChoice;

    	            do {

    	                System.out.println("\n========== CUSTOMER MENU ==========");
    	                System.out.println("1. View Account");
    	                System.out.println("2. Deposit");
    	                System.out.println("3. Withdraw");
    	                System.out.println("4. Check Balance");
    	                System.out.println("5. Delete Account");
    	                System.out.println("6. Logout");

    	                System.out.print("Enter Choice: ");
    	                customerChoice = sc.nextInt();

    	                switch (customerChoice) {

    	                case 1:

    	                    System.out.println(dao.getAccount(accountNo));
    	                    break;

    	                case 2:

    	                    System.out.print("Enter Deposit Amount: ");
    	                    double depositAmount = sc.nextDouble();

    	                    dao.deposit(accountNo, depositAmount);
    	                    break;

    	                case 3:

    	                    System.out.print("Enter Withdrawal Amount: ");
    	                    double withdrawAmount = sc.nextDouble();

    	                    dao.withdraw(accountNo, withdrawAmount);
    	                    break;

    	                case 4:

    	                    double currentBalance = dao.checkBalance(accountNo);

    	                    System.out.println("Current Balance : ₹" + currentBalance);
    	                    break;

    	                case 5:

    	                    boolean deleted = dao.deleteAccount(accountNo);

    	                    if (deleted) {
    	                        System.out.println("Account Deleted Successfully.");
    	                        customerChoice = 6;
    	                    }

    	                    break;

    	                case 6:

    	                    System.out.println("Logged Out Successfully.");
    	                    break;

    	                default:

    	                    System.out.println("Invalid Choice!");

    	                }

    	            } while (customerChoice != 6);

    	        } else {

    	            System.out.println("Invalid Account Number or PIN!");

    	        }

    	        break;

    	    case 3:

    	        System.out.println("Thank You For Using Banking System!");
    	        break;

    	    default:

    	        System.out.println("Invalid Choice!");

    	    }

    	} while (choice != 3);

    	sc.close();
    }

}