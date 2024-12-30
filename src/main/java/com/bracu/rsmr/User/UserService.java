package com.bracu.rsmr.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getRoles() == null){
            user.setRoles(new ArrayList<>(Arrays.asList("Customer")));
        }
        userRepository.save(user);
        if(user.getRoles().contains("Customer")){
              Account account = new Account(1000D,false, (long)((Math.random() * 100) + 1));
              account.setUser(user);
              user.setAccount(account);
              accountService.createAccount(account);
        }
        return user;
    }

    public boolean  addRole(User user,String role){
        List<String> roles = user.getRoles();
        if(roles.contains(role))
            return false;
        roles.add(role);
        userRepository.save(user);
        return true;
    }

    public boolean  removeRole(User user,String role){
        List<String> roles = user.getRoles();
        if(!roles.contains(role))
            return false;
        roles.remove(role);
        userRepository.save(user);
        return true;
    }

    public boolean checkUser(User user){
        Optional<User> dUser = userRepository.findByUsername(user.getUsername());
        if(dUser.isPresent())
            return bCryptPasswordEncoder.matches(user.getPassword(), dUser.get().getPassword());
        return false;
    }

    public void toggleMod(User user,String role){
        List<String> roles = user.getRoles();
        if(roles.contains(role))
            roles.remove(role);
        else
            roles.add(role);
        userRepository.save(user);
    }

    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    public User securityContext(){
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        String username = authenticated.getName();
        User user = userRepository.findByUsername(username).get();
        return user;
    }

}
