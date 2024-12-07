package com.bracu.rsmr.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.testMethod(new User("Rifat","pixel"));
        userService.testMethod(new User("Sishir","Xpress"));
    }
}
