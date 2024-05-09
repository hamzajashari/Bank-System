package com.bank.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import static com.bank.Model.Enum.FeeType.FLAT;
import static com.bank.Model.Enum.FeeType.PERCENT;

import com.bank.Exception.InsufficientFundsException;
import com.bank.Model.Account;
import com.bank.Model.Bank;
import com.bank.Model.Transaction;
import com.bank.Model.Enum.FeeType;
import com.bank.Repository.BankRepository;
import com.bank.Repository.TransactionRepository;
import com.bank.Service.IService.IBankService;


@Service
public class BankService implements IBankService {

    private final BankRepository repository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BankService(BankRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Bank create(Bank bank) {
        repository.save(bank);
        return bank;
    }

    @Override
    public Account create(String name, Account account) {
        var bank = repository.findBankByName(name).orElseThrow();
        bank.addAccount(account);
        return account;
    }

    @Override
    public Transaction create(String name, Transaction transaction, FeeType feeType) {
        var bank = repository.findBankByName(name).orElseThrow();
        performTransaction(bank, transaction, feeType);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Account> getAll(String name) {
        var bank = repository.findBankByName(name).orElseThrow();
        return bank.accounts.stream().toList();
    }

    @Override
    public Account checkBalance(String name, String accountId) {
        var bank = repository.findBankByName(name).orElseThrow();
        var account = bank.getAccountById(accountId);
        return account;
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountId(String id) {
        return transactionRepository.findAllByOriginatingAccountId(id).stream().toList();
    }

    @Override
    public BigDecimal getTotalTransferAmount(String name) {
        var bank = repository.findBankByName(name).orElseThrow();
        var totalFeeAmount = bank.getFeeAmount();
        return totalFeeAmount;
    }

    @Override
    public BigDecimal getTotalFeeAmount(String name) {
        var bank = repository.findBankByName(name).orElseThrow();
        var totalTransferAmount = bank.getAmount();
        return totalTransferAmount;
    }

    @Override
    public void performTransaction(Bank bank, Transaction transaction, FeeType feeType) {
        var amount = transaction.getAmount();
        var accountFrom = bank.getAccountById(transaction.getOriginatingAccountId());
        var accountTo = bank.getAccountById(transaction.getResultingAccountId());
        var flatFee = bank.getFlatFeeAmount();
        var percentFee = bank.getPercentFeeValue();
        accountFrom.subtractFromBalance(amount);
        if (feeType.equals(FLAT)) {
            bank.addToTransactionFeeAmount(flatFee);
            transaction.calculateTransactionFee(flatFee, feeType);
        } else if (feeType.equals(PERCENT)) {
            bank.addToTransactionFeeAmount(transaction.calculatePercentFee(percentFee));
            transaction.calculateTransactionFee(percentFee, feeType);
        }
        try {
            bank.addToTransferAmount(transaction.getAmount());
        } catch (InsufficientFundsException e) {
            System.err.println("Transaction failed: Insufficient funds in the originating account.");
            e.printStackTrace(); 
        }
        accountTo.addToBalance(transaction.getAmount());
    }
}
