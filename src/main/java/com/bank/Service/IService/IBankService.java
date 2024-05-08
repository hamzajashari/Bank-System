package com.bank.Service.IService;

import com.bank.Model.Account;

public interface IBankService {
    void createBank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue);
    void addAccount(Account account);
    Account getAccountById(String accountId);
    double getTotalTransactionFeeAmount();
    double getTotalTransferAmount();
}