package com.bracu.rsmr.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        if(user.getRoles().contains("Customer")){
              Account account = new Account(1000D,false, (long)((Math.random() * 100) + 1));
              account.setUser(user);
              user.setAccount(account);
              accountRepository.save(account);
        }
    }

    public boolean checkUser(User user){
        Optional<User> dUser = userRepository.findByUsername(user.getUsername());
        if(dUser.isPresent())
            return bCryptPasswordEncoder.matches(user.getPassword(), dUser.get().getPassword());
        return false;
    }

}
