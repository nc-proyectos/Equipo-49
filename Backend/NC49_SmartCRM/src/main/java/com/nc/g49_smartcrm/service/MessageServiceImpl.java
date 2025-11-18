package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.MessageRequest;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.exception.MessageNotFoundException;
import com.nc.g49_smartcrm.mapper.MessageMapper;
import com.nc.g49_smartcrm.model.Conversation;
import com.nc.g49_smartcrm.model.Message;
import com.nc.g49_smartcrm.repository.ConversationRepository;
import com.nc.g49_smartcrm.repository.MessageRepository;
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
}
