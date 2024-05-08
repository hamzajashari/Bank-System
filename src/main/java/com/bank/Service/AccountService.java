package com.bank.Service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.bank.Model.Account;
import com.bank.Model.SavingsAccount;
import com.bank.Model.Transaction;
import com.bank.Service.IService.IAccountService;

@Service
public class AccountService implements IAccountService {
    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public void createAccount(String accountId, String userName, double initialBalance) {
        SavingsAccount account = new SavingsAccount(accountId, userName, initialBalance);
        accounts.put(accountId, account);
    }

    @Override
    public double checkAccountBalance(String accountId) {
        Account account = accounts.get(accountId);
        if (account != null) {
            return account.getBalance();
        } else {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
    }

    @Override
    public List<Transaction> getAccountTransactionHistory(String accountId) {
        Account account = accounts.get(accountId);
        if (account != null) {
            return account.getTransactionHistory();
        } else {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
    }
}
