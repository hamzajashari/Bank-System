package com.bank.Model;

import static java.math.BigDecimal.ZERO;
import javax.persistence.*;
import java.math.BigDecimal;

import com.bank.Exception.InvalidAmountException;
import com.bank.Model.Abstract.AbstractModel;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends AbstractModel {

    @Column(name = "user", nullable = false)
    private String user;
  
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
  
    @Transient
    public void addToBalance(BigDecimal amount) {
      this.balance = this.balance.add(amount);
    }
  
    @Transient
    public void subtractFromBalance(BigDecimal amount) {
      if (balance.subtract(amount).compareTo(ZERO) < 0) {
        throw InvalidAmountException.notEnoughFunds(amount);
      }
      this.balance = this.balance.subtract(amount);
    }
    
}