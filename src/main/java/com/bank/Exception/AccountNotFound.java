package com.bank.Exception;

public class AccountNotFound extends RuntimeException {

    private AccountNotFound(String message) {
      super(message);
    }
  
    public static AccountNotFound withId(String id) {
      return new AccountNotFound("Account not found with id: " + id);
    }
  }
