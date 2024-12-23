package com.bracu.rsmr.Pages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserService;

@Controller
public class UserPages {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        User user = userService.securityContext();
        model.addAttribute("user", user);
        List<String> roles = user.getRoles();
        if(roles.contains("Customer"))
            return "index";
        else
            return "mindex";
    }

    @GetMapping("/transfer")
    public String transfer(Model model) {
        User user = userService.securityContext();
        model.addAttribute("userAccount", user.getAccount().getAccountId());
        model.addAttribute("user", user);
        return "transfer";
    }

}
