package com.bank.Service;

import org.springframework.stereotype.Service;

import com.bank.Service.IService.ITransactionService;

@Service
public class TransactionService implements ITransactionService{

    @Override
    public void performTransaction(String fromAccountId, String toAccountId, double amount, String transactionReason) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performTransaction'");
    }
    
}
