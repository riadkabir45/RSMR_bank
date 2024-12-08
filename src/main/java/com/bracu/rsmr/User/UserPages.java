package com.bracu.rsmr.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPages {
    @GetMapping("/")
    public String index(Model model) {
        int randomNumber = (int) (Math.random() * 100) + 1;
        model.addAttribute("randomNumber", randomNumber);
        return "index";
    }
}
