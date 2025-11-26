package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ConversationResponse;
import com.nc.g49_smartcrm.dto.ConversationStartRequest;
import com.nc.g49_smartcrm.exception.ContactNotFoundException;
import com.nc.g49_smartcrm.exception.ConversationAlreadyClosedException;
import com.nc.g49_smartcrm.exception.ConversationNotFoundException;
import com.nc.g49_smartcrm.exception.UserNotFoundException;
import com.nc.g49_smartcrm.mapper.ConversationMapper;
import com.nc.g49_smartcrm.model.Contact;
import com.nc.g49_smartcrm.model.Conversation;
import com.nc.g49_smartcrm.model.ConversationStatus;
import com.nc.g49_smartcrm.model.User;
import com.nc.g49_smartcrm.repository.ContactRepository;
import com.nc.g49_smartcrm.repository.ConversationRepository;
import com.nc.g49_smartcrm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ConversationMapper conversationMapper;
    private final ContactRepository contactRepository;

    @Override
    public List<ConversationResponse> getAllConversations() {
        return conversationRepository.findAll().stream().map(conversationMapper::toDto).toList();
    }

    @Override
    public ConversationResponse getConversationById(long id) {
        return conversationMapper.toDto(conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id)));
    }

    @Override
    public ConversationResponse startConversation(ConversationStartRequest request) {

        Conversation savedConversation = createConversation(request);

        return conversationMapper.toDto(savedConversation);
    }

    @Transactional
    @Override
    public ConversationResponse closeConversation(Long id) {

        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new ConversationNotFoundException(id));

        if (conversation.getStatus() == ConversationStatus.CLOSED) {
            throw new ConversationAlreadyClosedException(id);
        }

        conversation.setStatus(ConversationStatus.CLOSED);
        conversation.setClosedAt(Instant.now());

        Conversation updated = conversationRepository.save(conversation);
        return conversationMapper.toDto(updated);
    }

    @Override
    public ConversationResponse findByContactPhoneOrStartNewConversation(String phoneNumber,ConversationStartRequest request) {
        Conversation conversation=conversationRepository
                .findByContact_PhoneAndStatus(phoneNumber,ConversationStatus.OPEN)
                .orElseGet(()->createConversation(request));
        return conversationMapper.toDto(conversation);
    }

    private Conversation createConversation(ConversationStartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Contact contact=contactRepository.findById(request.getContactId())
                .orElseThrow(()->new ContactNotFoundException(request.getContactId()));

        Conversation conversation = Conversation.builder()
                .subject(request.getSubject())
                .channel(request.getChannel())
                .status(ConversationStatus.OPEN)
                .contact(contact)
                .user(user)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        return conversationRepository.save(conversation);
    }
}
