package com.bank.Service;

import org.springframework.stereotype.Service;

import com.bank.Model.Account;
import com.bank.Service.IService.IBankService;

@Service
public class BankService implements IBankService {

    @Override
    public void createBank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBank'");
    }

    @Override
    public void addAccount(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAccount'");
    }

    @Override
    public Account getAccountById(String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountById'");
    }

    @Override
    public double getTotalTransactionFeeAmount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalTransactionFeeAmount'");
    }

    @Override
    public double getTotalTransferAmount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalTransferAmount'");
    }
    
}
