package com.bank.Model;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.bank.Exception.InsufficientFundsException;

@EntityScan
public abstract class Account {
    private String accountId;
    private String userName;
    private double balance;

    public Account(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract method for performing a transaction
    public abstract void performTransaction(double amount, String transactionReason) throws InsufficientFundsException;
    // Abstract method for checking account balance
    public abstract double checkBalance();
    // Abstract method for getting transaction history
    public abstract List<Transaction> getTransactionHistory();
}