package com.bracu.rsmr.Account;

import java.net.URI;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> transferAmount(@RequestParam("srcId") String srcId,
        @RequestParam("dstId") String dstId,
        @RequestParam("amount") Double amount
            ) throws Exception{
        Transaction transaction = new Transaction(srcId,dstId,amount);
        System.out.println(transaction);
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        Account account = userRepository.findByUsername(authenticated.getName()).orElseThrow(() -> new AccountException("User not found")).getAccount();

        transaction.setSrcId(account.getAccountId());;
        accountService.transferAmount(transaction);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/transfer"));
        return new ResponseEntity<>("Transfer Successfull",headers,HttpStatus.FOUND);
    }
}
