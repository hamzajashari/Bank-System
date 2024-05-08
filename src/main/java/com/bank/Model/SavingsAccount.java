package com.bank.Model;

import javax.persistence.Entity;
import java.util.*;
import com.bank.Exception.InsufficientFundsException;

@Entity
public class SavingsAccount extends Account {

    // Additional fields specific to savings account, if any

    public SavingsAccount(String accountId, String userName, double balance) {
        super(accountId, userName, balance);
    }

    @Override
    public void performTransaction(double amount, String transactionReason) throws InsufficientFundsException {
        // Implement transaction logic for savings account
    }

    @Override
    public double checkBalance() {
        // Implement balance checking logic for savings account
        return getBalance();
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        // Implement transaction history retrieval logic for savings account
        return null; // Replace with actual implementation
    }
}