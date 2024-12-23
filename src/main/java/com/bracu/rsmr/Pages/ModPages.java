package com.bracu.rsmr.Pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
        model.addAttribute("transaction",transactionRepository.findByTransId(id).get());
        return "transback";
    }
}
