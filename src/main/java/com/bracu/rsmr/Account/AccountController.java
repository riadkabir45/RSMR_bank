package com.bracu.rsmr.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bracu.rsmr.Transaction.Transaction;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(@RequestBody Transaction transaction) throws Exception{
        accountService.transferAmount(transaction);
        System.err.println("Done");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
