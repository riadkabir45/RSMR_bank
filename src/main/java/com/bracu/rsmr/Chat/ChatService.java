package com.bracu.rsmr.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.User.UserService;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    public Chat sendText(Long sender ,Long link ,String text){
        Chat chat = new Chat();
        chat.setLink(link);
        chat.setSender(sender);
        chat.setText(text);
        chatRepository.save(chat);
        return chat;
    }

    public List<Chat> getChat(Long link){
        List<Chat> chats = chatRepository.findAllByLink(link);
        return chats;
    }
}
