package com.bracu.rsmr.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
