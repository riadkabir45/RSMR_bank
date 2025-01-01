package com.bracu.rsmr.Card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/approve/{id}")
    public ResponseEntity<?> approveCard(@PathVariable("id") Long id){
        cardService.approveCard(id);
        System.out.println("CardController.approveCard()"+id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
