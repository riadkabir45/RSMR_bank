package com.bracu.rsmr.Card;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.Account;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createCard(Account account){
        Card card = new Card();
        card.generateCardId();
        LocalDate expiryDate = LocalDate.now().plusYears(4);
        card.setExpiryDate(expiryDate);
        card.setOwner(account);
        cardRepository.save(card);
        return card;
    }

    public Card requestCard(Account account,String type){
        Card card = new Card();
        card.generateCardId();
        LocalDate expiryDate = LocalDate.now().plusYears(4);
        card.setExpiryDate(expiryDate);
        card.setApproved(false);
        card.setOwner(account);
        card.setCardType(type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase());
        cardRepository.save(card);
        return card;
    }

    public List<Card> listApproval(boolean approved){
        if(approved)
            return cardRepository.findByApprovedIsTrue();
        else
            return cardRepository.findByApprovedIsFalse();
    }

    public List<Card> approvedUseCards(Account account){
        return cardRepository.findByOwnerAndApprovedIsTrue(account);
    }

    public void approveCard(Long id){
        Card card = cardRepository.findById(id).get();
        card.setApproved(true);
        cardRepository.save(card);

    }
}
