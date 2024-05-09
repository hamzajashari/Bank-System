package com.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.Model.Transaction;
import java.util.*;;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByOriginatingAccountId(@Param("id") String id);
}