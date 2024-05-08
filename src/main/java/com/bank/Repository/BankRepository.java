package com.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.Model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}