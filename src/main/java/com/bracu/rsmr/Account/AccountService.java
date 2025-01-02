package com.bracu.rsmr.Account;

import java.util.Arrays;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Card.CardService;
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

    @Autowired
    private CardService cardService;

    public boolean createAccount(Account account){
        account.setAccountId(UUID.randomUUID().toString());
        account.getCards().add(cardService.createCard(account));
        accountRepository.save(account);
        return true;
    }

    public void transferAmount(String fromAccountId, String toAccountId, double amount) throws Exception {
        if(fromAccountId.equals(toAccountId))
            throw new IllegalArgumentException("Invalid Account ID");
        Account fromAccount = accountRepository.findByAccountId(fromAccountId).orElseThrow(() -> new AccountNotFoundException());
        Account toAccount = accountRepository.findByAccountId(toAccountId).orElseThrow(() -> new AccountNotFoundException());

        if(fromAccount.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient Balance");
        if(amount <= 0)
            throw new IllegalArgumentException("Invalid Amount");
        

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction transaction = new Transaction(fromAccountId, toAccountId, amount);
        transactionService.createTransaction(transaction);

        accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
    }

    public void backTransferAmount(Long id) throws Exception{
        Transaction transaction = transactionService.deleteTransfer(id);
        Account fromAccount = accountRepository.findByAccountId(transaction.getDstId()).orElseThrow(() -> new AccountNotFoundException());
        Account toAccount = accountRepository.findByAccountId(transaction.getSrcId()).orElseThrow(() -> new AccountNotFoundException());
        Double amount = transaction.getAmount();
        try{
            if(fromAccount.getBalance() < amount)
                throw new IllegalArgumentException("Insufficient Balance");
            if(amount <= 0)
                throw new IllegalArgumentException("Invalid Amount");
            

            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            accountRepository.saveAll(Arrays.asList(fromAccount, toAccount));
        } catch(IllegalArgumentException e) {
            throw e;
        } catch(Exception e){
            transactionService.createTransaction(transaction);
        }
        
    }
    

    public void transferAmount(Transaction transaction) throws Exception{
        transferAmount(transaction.getSrcId(), transaction.getDstId(), transaction.getAmount());
    }

    public Account requestCard(Account account,String type){
        account.getCards().add(cardService.requestCard(account,type));
        accountRepository.save(account);
        return account;
    }

    public Account cardPayment(Account account,int amount){
        if(account.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient Balance");
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
        return account;
    }

    public Account addBalance(Account account, Double balance){
        account.setBalance(account.getBalance()+balance);
        accountRepository.save(account);
        return account;
    }

    public Account deductBalance(Account account, Double balance){
        if (account.getBalance() < balance)
            throw new IllegalArgumentException("Insufficient Balance");
        account.setBalance(account.getBalance()-balance);
        accountRepository.save(account);
        return account;
    }
}
