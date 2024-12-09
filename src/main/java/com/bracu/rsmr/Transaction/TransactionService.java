package com.bracu.rsmr.Transaction;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public boolean createTransaction(Transaction transaction){
        transaction.setTransId(UUID.randomUUID().toString());
        transactionRepository.save(transaction);
        return true;
    }
}
