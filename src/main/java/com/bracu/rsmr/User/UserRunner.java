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
        userService.createUser(new User("Rifat","pixel"));
        userService.createUser(new User("Sishir","Xpress"));
        userService.createUser(new User("Nabi","Crypto"));
        userService.createUser(new User("Aritra","Dhuk"));
        userService.createUser(new User("Jahan","Misti"));
        userService.createUser(new User("Sajid","Flex"));
        userService.createUser(new User("Jenith","Gym"));
        userService.createUser(new User("Aowfi","MyNigga"));
    }
}
