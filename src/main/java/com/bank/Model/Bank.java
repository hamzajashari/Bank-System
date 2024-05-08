package com.bank.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.bank.Exception.InsufficientFundsException;

@EntityScan
public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public String getBankName() {
        return bankName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public double getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public double getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    // Method to perform a transaction
    public void performTransaction(Account fromAccount, Account toAccount, double amount, String transactionReason) throws InsufficientFundsException {
        // Check if fromAccount has enough balance
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in account: " + fromAccount.getAccountId());
        }

        // Calculate transaction fee
        double transactionFee = transactionFlatFeeAmount + (amount * transactionPercentFeeValue / 100);

        // Deduct amount + fee from fromAccount
        double amountWithFee = amount + transactionFee;
        fromAccount.performTransaction(-amountWithFee, transactionReason);

        // Add amount to toAccount
        toAccount.performTransaction(amount, transactionReason);

        // Update total transaction fee and transfer amount
        totalTransactionFeeAmount += transactionFee;
        totalTransferAmount += amount;
    }

    // Method to add an account to the bank
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Method to get account by ID
    public Account getAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
}