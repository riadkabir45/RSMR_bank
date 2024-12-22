package com.bracu.rsmr.Transaction;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transId;
    private String srcId;
    private String dstId;
    private Double amount;
    private LocalDateTime date = LocalDateTime.now();

    public Transaction(String srcId, String dstId, Double amount) {
        this.srcId = srcId;
        this.dstId = dstId;
        this.amount = amount;
    }
}
