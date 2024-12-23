package com.bracu.rsmr.Pages;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserRepository;
import com.bracu.rsmr.User.UserService;

@Controller
@RequestMapping("/admin")
public class AdminPages {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/modpage")
    public String modMg(Model model) {
        List<User> allUsers = userRepository.findAll();

        List<User> filteredUsers = allUsers.stream()
                .filter(user -> !user.getRoles().contains("Moderator"))
                .collect(Collectors.toList());
        User user = userService.securityContext();
        model.addAttribute("user", user);
        model.addAttribute("users", filteredUsers);
        return "modPage";
    }
}
