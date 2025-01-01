package com.bracu.rsmr.Card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardId;
    private LocalDate expiryDate;
    private String cardType = "Internal";

    @PostConstruct
    public void generateCardId() {
        long min = 1000000000000000L; 
        long max = 9999999999999999L;
        Random random = new Random();
        long randomLong = random.nextLong(min,max);
        this.cardId =  String.valueOf(randomLong);
    }

    public String getExpiryDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return formatter.format(this.expiryDate);
    }
}