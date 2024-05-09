package com.bank.Model.Account;

import javax.persistence.*;


import java.math.BigDecimal;

import com.bank.Exception.InvalidAmountException;
import com.bank.Model.Bank;
import com.bank.Model.Abstract.AbstractModel;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = {"user"}))
@Getter
@Setter
public class Account extends AbstractModel {

    @Column(name = "user", nullable = false)
    private String user;
  
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
  
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "bank_id")
    private Bank bank;
  
    // Transient methods for balance modification
    @Transient
    public void addToBalance(BigDecimal amount) {
      this.balance = this.balance.add(amount);
    }
  
    @Transient
    public void subtractFromBalance(BigDecimal amount) {
      if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
        throw InvalidAmountException.notEnoughFunds(amount);
      }
      this.balance = this.balance.subtract(amount);
    }
}