package com.bank.Model;

import java.util.List;

import com.bank.Exception.InsufficientFundsException;
import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    @Id
    private String accountId;
    private String userName;
    private double balance;

    public Account(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
    }
    
    public abstract void performTransaction(double amount, String transactionReason) throws InsufficientFundsException;

    public abstract double checkBalance();

    public abstract List<Transaction> getTransactionHistory();

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}