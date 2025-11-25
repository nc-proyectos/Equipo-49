package com.nc.g49_smartcrm.repository;

import com.nc.g49_smartcrm.model.Conversation;
import com.nc.g49_smartcrm.model.ConversationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByContact_PhoneAndStatus(String phone, ConversationStatus status);
}
