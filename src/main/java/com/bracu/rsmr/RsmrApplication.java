package com.bracu.rsmr;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bracu.rsmr.Account.AccountService;
import com.bracu.rsmr.Card.CardService;
import com.bracu.rsmr.CashPackage.CashPackage;
import com.bracu.rsmr.CashPackage.CashPackageService;
import com.bracu.rsmr.Chat.ChatService;
import com.bracu.rsmr.ChatLink.ChatLink;
import com.bracu.rsmr.ChatLink.ChatLinkService;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserService;

@SpringBootApplication
@EnableScheduling
public class RsmrApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ChatLinkService chatLinkService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private CardService cardService;

    @Autowired
    private CashPackageService cashPackageService;

	public static void main(String[] args) {
		SpringApplication.run(RsmrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        System.out.println("========================================================");
		User rifat = userService.createUser(new User("Rifat", "pixel", "rifatmahamd1@gmail.com", Arrays.asList("Moderator")));
        User sishir = userService.createUser(new User("Sishir", "Xpress", "rifatmahamd1@gmail.com", Arrays.asList("Moderator")));
        User nabi = userService.createUser(new User("Nabi", "Crypto", "rifatmahamd1@gmail.com"));
        User aritra = userService.createUser(new User("Aritra", "Dhuk", "rifatmahamd1@gmail.com"));
        User jahan = userService.createUser(new User("Jahan", "Misti", "rifatmahamd1@gmail.com"));
        User sajid = userService.createUser(new User("Sajid", "Flex", "rifatmahamd1@gmail.com"));
        User jenith = userService.createUser(new User("Jenith", "Gym", "rifatmahamd1@gmail.com"));
        User aowfi = userService.createUser(new User("Aowfi", "MyNigga", "rifatmahamd1@gmail.com"));


        
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
        chatService.sendText(jahan.getId(), lJahan.getId(), "Ai chattttt");

        chatService.sendText(aritra.getId(), lSajid.getId(), "Hello sir, How can I help.");

        chatService.getChat(lJahan.getId());

        // Card test
        cardService.approveCard(cardService.requestCard(jahan.getAccount(), "Credit").getId());
        cardService.approveCard(cardService.requestCard(jahan.getAccount(), "Debit").getId());

        // CashPackage Test
        CashPackage jahanPKG =  cashPackageService.createDPS(jahan.getAccount(), 24, 1000L);
        //cashPackageService.createLoan(jahan.getAccount(), 12, 1000L);
        //cashPackageService.createEMI(jahan.getAccount(), 6, 1000L);

        //accountService.updateDPS();
        //accountService.updateDPS();
        System.out.println("========================================================");
	}

}
