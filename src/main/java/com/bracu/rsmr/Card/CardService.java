package com.bracu.rsmr.Card;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createCard(){
        Card card = new Card();
        card.generateCardId();
        LocalDate expiryDate = LocalDate.now().plusYears(4);
        card.setExpiryDate(expiryDate);
        cardRepository.save(card);
        return card;
    }
}
