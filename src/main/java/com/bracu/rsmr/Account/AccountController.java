package com.bracu.rsmr.Account;

import java.net.URI;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/transfer")
    public ResponseEntity<?> transferAmount(@RequestParam("dstId") String dstId,
        @RequestParam("amount") Double amount
            ) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        try {
            accountService.transferAmount(userService.securityContext().getAccount().getAccountId(), dstId, amount);   
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
