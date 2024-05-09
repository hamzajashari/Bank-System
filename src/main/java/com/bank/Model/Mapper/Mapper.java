package com.bank.Model.Mapper;

import org.springframework.stereotype.Component;

import com.bank.Model.Account.Account;
import com.bank.Model.Account.AccountRequest;
import com.bank.Model.Account.AccountResponse;
import com.bank.Model.Transaction.Transaction;
import com.bank.Model.Transaction.TransactionRequest;
import com.bank.Model.Transaction.TransactionResponse;

@Component
public class Mapper {

  // Account
  public Account map(AccountRequest accountRequest) {
    var account = new Account();
    account.setUser(accountRequest.user());
    account.setBalance(accountRequest.balance());
    return account;
  }

  public AccountResponse map(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .user(account.getUser())
        .balance(account.getBalance())
        .build();
  }
  // Transaction

  public Transaction map(TransactionRequest request) {
    var transaction = new Transaction();
    transaction.setAmount(request.amount());
    transaction.setOriginatingAccountId(request.accountIdFrom());
    transaction.setResultingAccountId(request.accountIdTo());
    transaction.setTransactionReason(request.reason());
    return transaction;
  }

  public TransactionResponse map(Transaction transaction) {
    return TransactionResponse.builder()
        .id(transaction.getId())
        .amount(transaction.getAmount())
        .from(transaction.getOriginatingAccountId())
        .to(transaction.getResultingAccountId())
        .transactionReason(transaction.getTransactionReason())
        .build();
  }

}