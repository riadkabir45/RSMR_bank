package com.bracu.rsmr.Account;

import java.rmi.AccessException;
import java.util.Arrays;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Transaction.Transaction;
import com.bracu.rsmr.Transaction.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public boolean createAccount(Account account){
        account.setAccountId(UUID.randomUUID().toString());
        accountRepository.save(account);
        return true;
    }

    public void transferAmount(String fromAccountId, String toAccountId, double amount) throws Exception {
        Account fromAccount = accountRepository.findByAccountId(fromAccountId).orElseThrow(() -> new AccountNotFoundException());
        Account toAccount = accountRepository.findByAccountId(toAccountId).orElseThrow(() -> new AccountNotFoundException());

        if(fromAccount.getBalance() < amount)
            throw new AccessException("Insufficient Balance");

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction transaction = new Transaction(fromAccountId, toAccountId, amount);
        transactionService.createTransaction(transaction);

        accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
    }

    public void transferAmount(Transaction transaction) throws Exception{
        transferAmount(transaction.getSrcId(), transaction.getDstId(), transaction.getAmount());
    }
}
