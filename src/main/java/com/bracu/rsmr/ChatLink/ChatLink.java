package com.bracu.rsmr.ChatLink;

import com.bracu.rsmr.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class ChatLink {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @OneToOne
        private User customer;
        @ManyToOne
        private User support;
}
