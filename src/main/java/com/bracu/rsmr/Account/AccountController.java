package com.bracu.rsmr.Account;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bracu.rsmr.Transaction.Transaction;
import com.bracu.rsmr.User.UserRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(@RequestBody Transaction transaction) throws Exception{
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        Account account = userRepository.findByUsername(authenticated.getName()).orElseThrow(() -> new AccountException("User not found")).getAccount();

        transaction.setSrcId(account.getAccountId());;
        accountService.transferAmount(transaction);
        System.err.println("Done");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
