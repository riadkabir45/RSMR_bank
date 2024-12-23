package com.bracu.rsmr.Account;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/mod/account")
public class AccountModController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrans(@PathVariable("id") Long id) throws Exception{
        accountService.backTransferAmount(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/mod/backtransfer"));
        return new ResponseEntity<>("Transfer reversed Successfull",headers,HttpStatus.FOUND);
    }
}
