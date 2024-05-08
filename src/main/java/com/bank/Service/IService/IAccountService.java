package com.bank.Service.IService;

import java.util.List;
import com.bank.Model.Transaction;

public interface IAccountService {
    void createAccount(String accountId, String userName, double initialBalance);
    double checkAccountBalance(String accountId);
    List<Transaction> getAccountTransactionHistory(String accountId);
}