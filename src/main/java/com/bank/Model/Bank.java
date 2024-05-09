package com.bank.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import java.math.BigDecimal;

import com.bank.Exception.AccountNotFound;
import com.bank.Exception.InsufficientFundsException;
import com.bank.Model.Abstract.AbstractModel;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "banks")
@Getter
@Setter
public class Bank extends AbstractModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Account> accounts = new ArrayList<>();

    @Column(name = "fee_amount")
    private BigDecimal FeeAmount;

    @Column(name = "amount")
    private BigDecimal Amount;

    @Column(name = "flat_fee_amount", nullable = false)
    private BigDecimal FlatFeeAmount;

    @Column(name = "percent_fee_value", nullable = false)
    private BigDecimal PercentFeeValue;

    @Transient
    public void addToTransferAmount(BigDecimal amount) throws InsufficientFundsException {
        this.Amount = this.Amount.add(amount);
    }

    @Transient
    public void addToTransactionFeeAmount(BigDecimal amount) {
        this.FeeAmount = this.FeeAmount.add(amount);
    }
    
    public void addAccount(Account account) {
        account.setBank(this);
        this.accounts.add(account);
    }

    public Account getAccountById(String accountId) {
        return this.accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> AccountNotFound.withId(accountId));
    }
}