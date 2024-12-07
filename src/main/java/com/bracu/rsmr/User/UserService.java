package com.bracu.rsmr.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void testMethod(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);    
    }

    public boolean checkUser(User user){
        Optional<User> dUser = userRepository.findByUsername(user.getUsername());
        if(dUser.isPresent())
            return bCryptPasswordEncoder.matches(user.getPassword(), dUser.get().getPassword());
        return false;
    }

}
