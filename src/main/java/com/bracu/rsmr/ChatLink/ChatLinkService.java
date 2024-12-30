package com.bracu.rsmr.ChatLink;

import java.util.List;
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
    public List<ChatLink> pendingRequests(){
        return chatLinkRepository.findBySupportIsNull();
    }

    public ChatLink acceptRequests(Long user){
        User support = userService.findUser(user);

        return new ChatLink();
    }

    public ChatLink findLink(Long id){
        return chatLinkRepository.findById(id).get();
    }

    
    public ChatLink setSupport(Long id,User support){
        ChatLink link = chatLinkRepository.findById(id).get();
        link.setSupport(support);
        chatLinkRepository.save(link);
        return link;
    }

    public List<ChatLink> unreadChats(){
        for (ChatLink link : chatLinkRepository.findByIsreadIsFalse()) {
            System.out.println(link);
        }
        return chatLinkRepository.findByIsreadIsFalse();
    }
}
