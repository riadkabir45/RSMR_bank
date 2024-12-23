package com.bracu.rsmr.User;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkUser(@RequestBody User user){
        if(!userService.checkUser(user))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/signup",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signup(User user){
        HttpHeaders headers = new HttpHeaders();
        try {
            userService.createUser(user);
            headers.setLocation(URI.create("/"));
        } catch (DataIntegrityViolationException e) {
            headers.setLocation(URI.create("/signup?error="+"Username taken".replace(" ", "%20")));
        } catch (Exception e) {
            headers.setLocation(URI.create("/signup?error="+"Server error".replace(" ", "%20")));
        }
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

}
