package com.bracu.rsmr.Transaction;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.AccountService;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public boolean createTransaction(Transaction transaction){
        transaction.setTransId(UUID.randomUUID().toString());
        transactionRepository.save(transaction);
        return true;
    }

    public Transaction deleteTransfer(Long id) throws Exception{
        Transaction transaction = transactionRepository.findById(id).get();
        transactionRepository.deleteById(id);
        return transaction;
    }
}
