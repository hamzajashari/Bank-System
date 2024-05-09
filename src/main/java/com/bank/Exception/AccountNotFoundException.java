package com.bank.Exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
      super(message);
    }
  
    public static AccountNotFoundException withId(String id) {
      return new AccountNotFoundException("Account not found with id: " + id);
    }
  }
