package com.bracu.rsmr.Account;

import java.net.URI;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bracu.rsmr.Transaction.Transaction;
import com.bracu.rsmr.User.UserService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/transfer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> transferAmount(Transaction transaction) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        try {
            transaction.setSrcId(userService.securityContext().getAccount().getAccountId());
            accountService.transferAmount(transaction);   
            headers.setLocation(URI.create("/transfer"));
        } catch (IllegalArgumentException e) {
            headers.setLocation(URI.create("/transfer?error="+e.getMessage().replace(" ", "%20")));
        } catch (AccountNotFoundException e) {
            headers.setLocation(URI.create("/transfer?error="+"Reciever Account not found".replace(" ", "%20")));
        } catch (Exception e) {
            headers.setLocation(URI.create("/transfer?error="+e.getClass().getSimpleName()));
        }
        return new ResponseEntity<>("Transfer Successfull",headers,HttpStatus.FOUND);
    }

}
