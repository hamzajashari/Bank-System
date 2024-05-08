package com.bank.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Transaction {
    private double amount;
    private String originatingAccountId;
    private String resultingAccountId;
    private String transactionReason;

    public Transaction(double amount, String originatingAccountId, String resultingAccountId,
            String transactionReason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }

    public double getAmount() {
        return amount;
    }

    public String getOriginatingAccountId() {
        return originatingAccountId;
    }

    public String getResultingAccountId() {
        return resultingAccountId;
    }

    public String getTransactionReason() {
        return transactionReason;
    }
}
