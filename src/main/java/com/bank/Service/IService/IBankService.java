package com.bank.Service.IService;

import com.bank.Model.*;
import java.math.BigDecimal;
import com.bank.Model.Enum.FeeType;

import java.util.*;

public interface IBankService {
    Bank create(Bank bank);

    Account create(String name, Account account);

    Transaction create(String name, Transaction transaction, FeeType feeType);

    List<Account> getAll(String name);

    Account checkBalance(String name, String accountId);

    List<Transaction> getAllTransactionsByAccountId(String id);

    BigDecimal getTotalTransferAmount(String name);

    BigDecimal  getTotalFeeAmount(String name);

    void performTransaction(Bank bank, Transaction transaction, FeeType feeType);
}