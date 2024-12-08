package com.bracu.rsmr.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public boolean createAccount(Account account){
        accountRepository.save(account);
        return true;
    }
}
