package com.bracu.rsmr.Card;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountService;
import com.bracu.rsmr.User.UserService;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    @Lazy
    private AccountService accountService;

    @Autowired
    @Lazy
    private UserService userService;

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

    public Card approveCard(Long id){
        Card card = cardRepository.findById(id).get();
        card.setApproved(true);
        cardRepository.save(card);
        return card;
    }

    public Card payment(String cardId,int amount){
        Card card = cardRepository.findByCardId(cardId).get();
        if(card.getOwner().getUser() != userService.securityContext())
            throw new AccessDeniedException(cardId);
        if(card.getCardType().equals("Credit"))
            card.setBalance(card.getBalance()+amount);
        else if(card.getCardType().equals("Debit"))
            accountService.cardPayment(card.getOwner(), amount);
        else
            System.out.println(card.getCardType());
        return card;
    }
}
