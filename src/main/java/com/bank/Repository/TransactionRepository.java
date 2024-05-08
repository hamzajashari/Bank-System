package com.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.Model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}