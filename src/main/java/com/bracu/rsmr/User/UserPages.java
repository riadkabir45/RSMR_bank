package com.bracu.rsmr.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPages {
    @GetMapping("/")
    public String index(Model model) {
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        String username = authenticated.getName();
        System.err.println(username);
        model.addAttribute("username", username);
        return "index";
    }
}
