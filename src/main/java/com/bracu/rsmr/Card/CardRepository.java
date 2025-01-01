package com.bracu.rsmr.Card;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bracu.rsmr.Account.Account;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByApprovedIsTrue();
    List<Card> findByApprovedIsFalse();
    List<Card> findByOwnerAndApprovedIsTrue(Account account);
} 