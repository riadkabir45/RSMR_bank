package com.bracu.rsmr.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.ChatLink.ChatLink;
import com.bracu.rsmr.ChatLink.ChatLinkService;
import com.bracu.rsmr.User.UserService;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatLinkService chatLinkService;

    public Chat sendText(Long sender ,Long link ,String text){
        Chat chat = new Chat();
        chat.setLink(link);
        chat.setSender(sender);
        chat.setText(text);
        return sendText(chat);
    }

    public List<Chat> getChat(Long link){
        List<Chat> chats = chatRepository.findAllByLink(link);
        return chats;
    }

    public Chat sendText(Chat chat){
        ChatLink link = chatLinkService.findLink(chat.link);
        if(chat.sender == link.getCustomer().getId())
        chatLinkService.isRead(link, false);
        else if(chat.sender == link.getSupport().getId())
            chatLinkService.isRead(link, true);
        else
            throw new AccessDeniedException("Sender is not authorized!!");
        chatRepository.save(chat);
        return chat;
    }
}
