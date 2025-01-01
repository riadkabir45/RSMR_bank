package com.bracu.rsmr.Chat;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mod/links")
public class ChatModController {

    @PostMapping(value = "/open", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> deleteTrans(Long id) throws Exception{
        System.out.println("/mod/serve/"+id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/mod/serve/"+id));
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }
}
