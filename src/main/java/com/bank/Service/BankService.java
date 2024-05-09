package com.bank.Service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import javax.transaction.Transactional;

import static com.bank.Model.Enum.FeeType.FLAT;
import static com.bank.Model.Enum.FeeType.PERCENT;

import com.bank.Exception.AccountNotFoundException;
import com.bank.Exception.BankNotFoundException;
import com.bank.Exception.DuplicateEntryException;
import com.bank.Exception.InsufficientFundsException;
import com.bank.Model.Bank;
import com.bank.Model.Account.AccountRequest;
import com.bank.Model.Account.AccountResponse;
import com.bank.Model.Enum.FeeType;
import com.bank.Model.Mapper.Mapper;
import com.bank.Model.Transaction.Transaction;
import com.bank.Model.Transaction.TransactionRequest;
import com.bank.Model.Transaction.TransactionResponse;
import com.bank.Repository.BankRepository;
import com.bank.Repository.TransactionRepository;
import com.bank.Service.IService.IBankService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BankService implements IBankService {

    private final BankRepository bankRepository;
    private final TransactionRepository transactionRepository;
    private final Mapper mapper;

    @Transactional
    public Bank create(Bank bank) {
        if (bankRepository.findBankByName(bank.getName()).isPresent()) {
            throw new DuplicateEntryException("Bank with name " + bank.getName() + " already exists");
        }
        bankRepository.save(bank);
        return bank;
    }

    @Transactional
    public AccountResponse create(String name, AccountRequest accountRequest) {
        var bank = bankRepository.findBankByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));
        

        if (bank.accounts.stream().anyMatch(account -> account.getUser().equals(accountRequest.user()))) {
            throw new DuplicateEntryException("Account with name " + accountRequest.user() + " already exists.");
        }

        var account = mapper.map(accountRequest);
        bank.addAccount(account);
        return mapper.map(account);
    }

    @Transactional
    public TransactionResponse create(String name, TransactionRequest transactionRequest, FeeType feeType) {
        var bank = bankRepository.findBankByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));
        
        // You need to implement similar checks for transaction not found and duplicate transaction creation.
        
        var transaction = mapper.map(transactionRequest);
        performTransaction(bank, transaction, feeType);
        transactionRepository.save(transaction);
        return mapper.map(transaction);
    }

    @Transactional
    public List<AccountResponse> getAll(String name) {
        var bank = bankRepository.findBankByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));

        return bank.accounts.stream()
                .map(mapper::map).toList();
    }

    @Transactional
    public AccountResponse checkBalance(String name, String accountId) {
        var bank = bankRepository.findBankByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));
        var account = bank.getAccountById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
        }
        return mapper.map(account);
    }

    @Transactional
    public List<TransactionResponse> getAllTransactionsByAccountId(String id) {
        return transactionRepository.findAllByOriginatingAccountId(id).stream().map(mapper::map).toList();
    }

    @Transactional
    public BigDecimal getTotalTransferAmount(String name) {
        var bank = bankRepository.findBankByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));
        var totalFeeAmount = bank.getFeeAmount();
        return totalFeeAmount;
    }

    @Transactional
    public BigDecimal getTotalFeeAmount(String name) {
        var bank = bankRepository.findBankByName(name)
                .orElseThrow(() -> new BankNotFoundException(name));
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