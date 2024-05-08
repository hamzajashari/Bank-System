package com.bank.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.Model.Transaction;
import com.bank.Service.IService.IAccountService;

@Service
public class AccountService implements IAccountService {

    @Override
    public void createAccount(String accountId, String userName, double initialBalance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    @Override
    public double checkAccountBalance(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkAccountBalance'");
    }

    @Override
    public List<Transaction> getAccountTransactionHistory(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountTransactionHistory'");
    }
    
}
