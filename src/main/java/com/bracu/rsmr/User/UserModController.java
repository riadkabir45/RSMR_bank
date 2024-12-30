package com.bracu.rsmr.User;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountRepository;

@RestController
@RequestMapping("/api/mod/users")
public class UserModController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> toggleMod(@PathVariable("id") Long id){
        User user = userRepository.findById(id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/admin/modpage"));
        userService.toggleMod(user, "PartMod");
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

    @GetMapping("/disabled/{id}")
    public ResponseEntity<?> toggleActivation(@PathVariable("id") Long id){
        User user = userRepository.findById(id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/admin/adminpage"));
        if (user.getAccount().getDisabled()) {
            user.getAccount().setDisabled(false);
        } else {
            user.getAccount().setDisabled(true);
        }
        // userService.toggleMod(user, "PartMod");
        accountRepository.save(user.getAccount());
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

}
