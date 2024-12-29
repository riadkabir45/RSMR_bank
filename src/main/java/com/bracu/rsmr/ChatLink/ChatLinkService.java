package com.bracu.rsmr.ChatLink;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserService;

@Service
public class ChatLinkService {

    @Autowired
    private ChatLinkRepository chatLinkRepository;

    @Autowired
    private UserService userService;

    public ChatLink requestLink(User user){
        ChatLink newLink = new ChatLink();
        newLink.setCustomer(user);
        return chatLinkRepository.save(newLink);
    }

    public ChatLink requestLink(Long user){
        ChatLink newLink = new ChatLink();
        newLink.setCustomer(userService.findUser(user));
        return chatLinkRepository.save(newLink);
    }

    public ChatLink setSupport(ChatLink link,User user){
        link.setSupport(user);
        return chatLinkRepository.save(link);
    }

    public Optional<ChatLink> getByCustomerId(Long customer){
        return chatLinkRepository.findByCustomer(userService.findUser(customer));
    }
}
