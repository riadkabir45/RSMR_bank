package com.bracu.rsmr.Pages;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.bracu.rsmr.ChatLink.ChatLink;
import com.bracu.rsmr.ChatLink.ChatLinkService;
import com.bracu.rsmr.Transaction.TransactionRepository;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserService;


@Controller
@RequestMapping("/mod")
public class ModPages {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatLinkService chatLinkService;

    @GetMapping("/backtransfer")
    public String reverseTransfer(Model model) {
        User user = userService.securityContext();
        model.addAttribute("user", user);
        return "transback";
    }
    
    @PostMapping("/backtransfer")
    public String reverseTransferPost(Model model,@RequestParam("transactionId") String id) {
        User user = userService.securityContext();
        model.addAttribute("user", user);
        try {
            
        model.addAttribute("transaction",transactionRepository.findByTransId(id).get());
        } catch (NoSuchElementException e) {
            model.addAttribute("error","Transaction Id not found!");
        } catch (Exception e) {
            model.addAttribute("error","A server error occured!");
        }
        return "transback";
    }

    @GetMapping("/serve")
    public String supportList(Model model) {
        User user = userService.securityContext();
        List<ChatLink> link  = chatLinkService.unreadChats();
        model.addAttribute("links", link);
        model.addAttribute("user", user);
        model.addAttribute("action", "Open");
        model.addAttribute("actionURL", "/api/mod/links/open");
        return "requests";
    }

    @GetMapping("/serve/{id}")
    public String support(Model model,@PathVariable("id") Long id) {
        User user = userService.securityContext();
        model.addAttribute("link", chatLinkService.findLink(id));
        model.addAttribute("user", user);
        return "support";
    }
}
