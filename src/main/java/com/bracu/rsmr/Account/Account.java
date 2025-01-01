package com.bracu.rsmr.Account;

import java.util.ArrayList;
import java.util.List;

import com.bracu.rsmr.Card.Card;
import com.bracu.rsmr.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountId;
    private Double balance;
    private boolean disabled;
    private Long nId;
    @OneToMany
    private List<Card> cards = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account(Double balance, boolean disabled, Long nId) {
        this.balance = balance;
        this.disabled = true;
        this.nId = nId;
    }

    public boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean action) {
        this.disabled = action;
    }


}
