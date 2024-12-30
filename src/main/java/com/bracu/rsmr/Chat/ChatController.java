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

import com.bracu.rsmr.ChatLink.ChatLink;
import com.bracu.rsmr.ChatLink.ChatLinkService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatLinkService chatLinkService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getChats(@PathVariable("id") Long id){
        return new ResponseEntity<>(chatService.getChat(id),HttpStatus.OK);
    }

    @PostMapping(value = "/send", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> sendText(Chat chat){
        chatService.sendText(chat);
        ChatLink link = chatLinkService.findLink(chat.link);
        HttpHeaders headers = new HttpHeaders();
        if(link.getCustomer().getId() == chat.getSender())
            headers.setLocation(URI.create("/support"));
        else
            headers.setLocation(URI.create("/mod/serve/"+link.getId()));
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }
}
