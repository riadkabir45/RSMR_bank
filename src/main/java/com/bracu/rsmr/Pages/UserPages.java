package com.bracu.rsmr.Pages;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bracu.rsmr.Card.Card;
import com.bracu.rsmr.Card.CardService;
import com.bracu.rsmr.ChatLink.ChatLink;
import com.bracu.rsmr.ChatLink.ChatLinkService;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserService;

@Controller
public class UserPages {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatLinkService chatLinkService;

    @Autowired
    private CardService cardService;

    @GetMapping("/")
    public String index(Model model) {
        User user = userService.securityContext();
        model.addAttribute("user", user);
        List<String> roles = user.getRoles();
        List<ChatLink> links  = chatLinkService.pendingRequests();
        List<Card> cards = cardService.listApproval(false);
        model.addAttribute("cards", cards);
        model.addAttribute("links", links);
        if(roles.contains("Customer"))
            return "index";
        else
            return "mindex";
    }

    @GetMapping("/signup")
    public String login(Model model,@RequestParam(required = false) String error) {
        User user = new User();
        if(error != null)
            model.addAttribute("error",error);
        model.addAttribute("user",user);
        return "signup";
    }

    @GetMapping("/transfer")
    public String transfer(Model model,@RequestParam(required = false) String error) {
        System.out.println(error+"ERROR");
        User user = userService.securityContext();
        if(error != null)
            model.addAttribute("error",error);
        model.addAttribute("userAccount", user.getAccount().getAccountId());
        model.addAttribute("user", user);
        return "transfer";
    }

    @GetMapping("/support")
    public String support(Model model,@RequestParam(required = false) String error) {
        System.out.println(error+"ERROR");
        User user = userService.securityContext();
        Optional<ChatLink> link = chatLinkService.getByCustomerId(user.getId());
        if(error != null)
            model.addAttribute("error",error);
        if(link.isPresent())
            model.addAttribute("link", link.get());
        else
            model.addAttribute("link", null);
        model.addAttribute("user", user);
        return "support";
    }

    @GetMapping("/requests")
    public String requests(Model model,@RequestParam(required = false) String error) {
        User user = userService.securityContext();
        if(error != null)
            model.addAttribute("error",error);
        List<ChatLink> links  = chatLinkService.pendingRequests();
        model.addAttribute("links", links);
        model.addAttribute("user", user);
        model.addAttribute("action", "Accept");
        model.addAttribute("actionURL", "/api/links/accept");
        return "requests";
    }
    
    @GetMapping("/cards")
    public String cards(Model model,@RequestParam(required = false) String error) {
        User user = userService.securityContext();
        if(error != null)
            model.addAttribute("error",error);
        model.addAttribute("cards", cardService.approvedUseCards(userService.securityContext().getAccount()));
        model.addAttribute("user", user);
        return "cards";
    }

    @GetMapping("/cardRequest")
    public String cardRequest(Model model,@RequestParam(required = false) String error) {
        User user = userService.securityContext();
        if(error != null)
            model.addAttribute("error",error);
        model.addAttribute("user", user);
        return "cardRequest";
    }

}
