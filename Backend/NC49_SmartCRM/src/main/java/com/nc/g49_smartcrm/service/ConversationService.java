package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ConversationResponse;
import com.nc.g49_smartcrm.dto.ConversationStartRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ConversationService {
    List<ConversationResponse> getAllConversations();

    ConversationResponse getConversationById(long id);

    ConversationResponse startConversation(ConversationStartRequest request);

    @Transactional
    ConversationResponse closeConversation(Long id);

    ConversationResponse findByContactPhoneOrStartNewConversation(String phoneNumber, ConversationStartRequest request);
}
