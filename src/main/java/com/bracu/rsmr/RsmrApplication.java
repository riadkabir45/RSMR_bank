package com.bracu.rsmr;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bracu.rsmr.Account.AccountService;
import com.bracu.rsmr.Chat.ChatService;
import com.bracu.rsmr.ChatLink.ChatLink;
import com.bracu.rsmr.ChatLink.ChatLinkService;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserRepository;
import com.bracu.rsmr.User.UserService;

@SpringBootApplication
public class RsmrApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ChatLinkService chatLinkService;

    @Autowired
    private ChatService chatService;

	public static void main(String[] args) {
		SpringApplication.run(RsmrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User rifat = userService.createUser(new User("Rifat","pixel",Arrays.asList("Moderator")));
        User Sishir = userService.createUser(new User("Sishir","Xpress",Arrays.asList("Moderator")));
        User nabi = userService.createUser(new User("Nabi","Crypto"));
        User aritra = userService.createUser(new User("Aritra","Dhuk"));
        User jahan = userService.createUser(new User("Jahan","Misti"));
        User sajid = userService.createUser(new User("Sajid","Flex"));
        User jenith = userService.createUser(new User("Jenith","Gym"));
        User aowfi = userService.createUser(new User("Aowfi","MyNigga"));

        
        // Add PartMod
        userService.addRole(aowfi, "PartMod");
        userService.addRole(aritra, "PartMod");
        
        
        //accountService.transferAmount(rifat, aowfi, 500D);
        accountService.transferAmount(aowfi.getAccount().getAccountId(), jahan.getAccount().getAccountId(), 300D);
        accountService.transferAmount(nabi.getAccount().getAccountId(), aritra.getAccount().getAccountId(), 900D);

        // Test ChatLink
        ChatLink lAowfi = chatLinkService.requestLink(aowfi);
        ChatLink lJahan = chatLinkService.requestLink(jahan);
        ChatLink lSajid = chatLinkService.requestLink(sajid);
        // chatLinkService.setSupport(lJahan, rifat);
        // chatLinkService.setSupport(lAowfi, rifat);

        // Test Chat
        chatLinkService.setSupport(lJahan, aritra);
        chatLinkService.setSupport(lSajid, aritra);

        chatService.sendText(aritra.getId(), lJahan.getId(), "Hello sir, How can I help.");
        chatService.sendText(jahan.getId(), lJahan.getId(), "Ai chat amake chat");
        chatService.sendText(aritra.getId(), lJahan.getId(), "Sir this is very inappropriate");

        chatService.sendText(aritra.getId(), lSajid.getId(), "Hello sir, How can I help.");

        chatService.getChat(lJahan.getId());
	}

}
