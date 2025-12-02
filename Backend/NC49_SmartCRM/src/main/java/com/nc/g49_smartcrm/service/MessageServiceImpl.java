package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.*;
import com.nc.g49_smartcrm.exception.MessageNotFoundException;
import com.nc.g49_smartcrm.mapper.MessageMapper;
import com.nc.g49_smartcrm.model.*;
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
        if(SenderType.AGENT.equals(request.getSenderType())){
            message.setDirection(MessageDirection.OUTBOUND);
        }else{
            message.setDirection(MessageDirection.INBOUND);
        }
        message.setMessageStatus(MessageStatus.SENT);
        Message savedMessage = messageRepository.save(message);
        return messageMapper.toDto(savedMessage);
    }

    @Override
    public List<MessageResponse> getMessages(Long conversationId) {
        return List.of();
    }

    @Transactional
    @Override
    public MessageResponse saveMessage(DataMessage dataMessage) {
        //verifica si existe el contacto si no crea uno nuevo.
        ContactResponse contact = contactService
                    .findByPhoneOrCreateNewContact(dataMessage.phone(),
                            new ContactRequest(
                                    dataMessage.firstName(),
                                    dataMessage.lastName(),
                                    dataMessage.email(),
                                    dataMessage.phone(),
                                    ContactStatus.CLIENT,
                                    dataMessage.contactSource(),
                                    dataMessage.userId()
                            ));

        //verifica si hay una conversacion abierta o inicia una nueva.
        ConversationResponse conversation = conversationService
                .findByContactPhoneOrStartNewConversation(dataMessage.phone(),
                        new ConversationStartRequest(
                                dataMessage.userId(),
                                "subject",
                                contact.getId(),
                                dataMessage.channel(),
                                dataMessage.message()
                        )
                );

        Long senderId;
        if(SenderType.AGENT.equals(dataMessage.senderType())){
            senderId=dataMessage.userId();
        }else{
            senderId=contact.getId();
        }

        MessageRequest messageRequest = new MessageRequest(
                senderId,
                dataMessage.senderType(),
                dataMessage.message()
        );

        return addMessage(conversation.getId(), messageRequest);
    }
}
