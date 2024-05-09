package com.bank.Service.IService;

import com.bank.Model.*;
import com.bank.Model.Account.AccountRequest;
import com.bank.Model.Account.AccountResponse;

import java.math.BigDecimal;
import com.bank.Model.Enum.FeeType;
import com.bank.Model.Transaction.Transaction;
import com.bank.Model.Transaction.TransactionRequest;
import com.bank.Model.Transaction.TransactionResponse;

import java.util.*;

public interface IBankService {
    Bank create(Bank bank);

    AccountResponse create(String name, AccountRequest account);

    TransactionResponse create(String name, TransactionRequest transactionRequest, FeeType feeType);

    List<AccountResponse> getAll(String name);

    AccountResponse checkBalance(String name, String accountId);

    List<TransactionResponse> getAllTransactionsByAccountId(String id);

    BigDecimal getTotalTransferAmount(String name);

    BigDecimal  getTotalFeeAmount(String name);

    void performTransaction(Bank bank, Transaction transaction, FeeType feeType);
}