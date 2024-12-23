package com.bracu.rsmr.Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserRepository;

public class AdminPages {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/modpage")
    public String modMg(Model model) {
        List<User> allUsers = userRepository.findAll();

        List<User> filteredUsers = allUsers.stream()
                .filter(user -> !user.getRoles().contains("Moderator"))
                .collect(Collectors.toList());
        System.out.println(userRepository.findByUsername("Aowfi").get().getRoles());
        model.addAttribute("users", filteredUsers);
        return "modPage";
    }
}
