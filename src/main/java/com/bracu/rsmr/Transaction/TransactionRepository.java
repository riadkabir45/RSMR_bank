package com.bracu.rsmr.Transaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    Optional<Transaction> findByTransId(String tid);
}
