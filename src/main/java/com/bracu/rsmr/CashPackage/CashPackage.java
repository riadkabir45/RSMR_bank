package com.bracu.rsmr.CashPackage;

import java.time.LocalDate;

import com.bracu.rsmr.Account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CashPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Account account;
    private CashPackageType packageType;
    private int passed = 0;
    private Long amount;
    private LocalDate date = LocalDate.now();
    private LocalDate expiryDate;
    private boolean approved = false;
    private double interest = 0.20;
}
