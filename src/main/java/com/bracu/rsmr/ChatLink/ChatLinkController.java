package com.bracu.rsmr.ChatLink;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bracu.rsmr.User.UserService;

@RestController
@RequestMapping("/api/links")
public class ChatLinkController {
    
    @Autowired
    private ChatLinkService chatLinkService;

    @Autowired UserService userService;

    @PostMapping(value = "/request", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> createRequest(Long customerId){
        chatLinkService.requestLink(customerId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/support"));
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }

    @PostMapping(value = "/accept", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> acceptRequest(Long id){
        chatLinkService.setSupport(id,userService.securityContext());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/requests"));
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }
    
}
