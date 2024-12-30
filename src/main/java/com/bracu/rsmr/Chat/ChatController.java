package com.bracu.rsmr.Chat;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getChats(@PathVariable("id") Long id){
        return new ResponseEntity<>(chatService.getChat(id),HttpStatus.OK);
    }

    @PostMapping(value = "/send", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> sendText(Chat chat){
        chatService.sendText(chat);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/support"));
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }
}
