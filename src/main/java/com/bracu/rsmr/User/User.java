package com.bracu.rsmr.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bracu.rsmr.Account.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private List<String> roles;
    private int points = 0;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
    
    public User(String username, String password, String email, List<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = new ArrayList<>(roles);
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = new ArrayList<>(Arrays.asList("Customer"));
    }

    public String getEmail() {
        return email;
    }
    

}
