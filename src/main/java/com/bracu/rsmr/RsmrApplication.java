package com.bracu.rsmr;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountService;
import com.bracu.rsmr.User.User;
import com.bracu.rsmr.User.UserRepository;
import com.bracu.rsmr.User.UserService;

@SpringBootApplication
public class RsmrApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;


    @Autowired
    private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RsmrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.createUser(new User("Rifat","pixel",Arrays.asList("Moderator")));
        userService.createUser(new User("Sishir","Xpress",Arrays.asList("Moderator")));
        userService.createUser(new User("Nabi","Crypto"));
        userService.createUser(new User("Aritra","Dhuk"));
        userService.createUser(new User("Jahan","Misti"));
        userService.createUser(new User("Sajid","Flex"));
        userService.createUser(new User("Jenith","Gym"));
        userService.createUser(new User("Aowfi","MyNigga"));

        // //String rifat = userRepository.findByUsername("Rifat").get().getAccount().getAccountId();
		// String aowfi = userRepository.findByUsername("Aowfi").get().getAccount().getAccountId();
		// String jahan = userRepository.findByUsername("Jahan").get().getAccount().getAccountId();
		// String nabi = userRepository.findByUsername("Nabi").get().getAccount().getAccountId();
		// String aritra = userRepository.findByUsername("Aritra").get().getAccount().getAccountId();

        // //accountService.transferAmount(rifat, aowfi, 500D);
        // accountService.transferAmount(aowfi, jahan, 300D);
        // accountService.transferAmount(nabi, aritra, 900D);
	}

}
