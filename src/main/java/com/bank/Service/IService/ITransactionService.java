package com.bank.Service.IService;

public interface ITransactionService {
    void performTransaction(String fromAccountId, String toAccountId, double amount, String transactionReason);
}