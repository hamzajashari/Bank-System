package com.bank.Exception;

public class BankNotFoundException extends RuntimeException {

    public BankNotFoundException(String name) {
      super(name);
    }
  
    public static BankNotFoundException BankNotFound(String name) {
      return new BankNotFoundException("Bank not found with name: " + name);
    }
  }