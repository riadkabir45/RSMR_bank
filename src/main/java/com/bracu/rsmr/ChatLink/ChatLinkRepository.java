package com.bracu.rsmr.ChatLink;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bracu.rsmr.User.User;

@Repository
public interface ChatLinkRepository extends JpaRepository<ChatLink,Long> {
    Optional<ChatLink> findByCustomer(User user);
    List<ChatLink> findBySupportIsNull();
}
