package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.*;
import com.nc.g49_smartcrm.exception.MessageNotFoundException;
import com.nc.g49_smartcrm.mapper.MessageMapper;
import com.nc.g49_smartcrm.model.ContactStatus;
import com.nc.g49_smartcrm.model.Conversation;
import com.nc.g49_smartcrm.model.Message;
import com.nc.g49_smartcrm.repository.ConversationRepository;
import com.nc.g49_smartcrm.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private ConversationRepository conversationRepository;
    private MessageMapper messageMapper;
    private ConversationService conversationService;
    private ContactService contactService;

    @Override
    public MessageResponse getById(Long id) {

        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));

        return messageMapper.toDto(message);
    }

    @Override
    public MessageResponse addMessage(Long conversationId, MessageRequest request) {

        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new MessageNotFoundException(conversationId));

        Message message = messageMapper.toEntity(request);
        message.setConversation(conversation);
        message.setCreatedAt(Instant.now());
        Message savedMessage = messageRepository.save(message);
        return messageMapper.toDto(savedMessage);
    }

    @Override
    public List<MessageResponse> getMessages(Long conversationId) {
        return List.of();
    }

    @Transactional
    @Override
    public MessageResponse saveMessage(InboundMessage inboundMessage) {

        //verifica si existe el contacto si no crea uno nuevo.
        ContactResponse contact = contactService
                .findByPhoneOrCreateNewContact(inboundMessage.phone(),
                        new ContactRequest(
                                inboundMessage.firstName(),
                                inboundMessage.lastName(),
                                inboundMessage.email(),
                                inboundMessage.phone(),
                                ContactStatus.CLIENT,
                                inboundMessage.contactSource(),
                                inboundMessage.userId()
                        ));

        //verifica si hay una conversacion abierta o inicia una nueva.
        ConversationResponse conversation = conversationService
                .findByContactPhoneOrStartNewConversation(inboundMessage.phone(),
                        new ConversationStartRequest(
                                inboundMessage.userId(),
                                "subject",
                                contact.getId(),
                                inboundMessage.channel(),
                                inboundMessage.message()
                        )
                );

        MessageRequest messageRequest = new MessageRequest(
                contact.getId(),
                inboundMessage.senderType(),
                inboundMessage.message()
        );

        return addMessage(conversation.getId(), messageRequest);
    }
}
