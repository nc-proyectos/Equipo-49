package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.dto.SourceCountDto;
import com.nc.g49_smartcrm.model.ContactStatus;
import com.nc.g49_smartcrm.model.ConversationStatus;
import com.nc.g49_smartcrm.model.MessageDirection;
import com.nc.g49_smartcrm.repository.ContactRepository;
import com.nc.g49_smartcrm.repository.ConversationRepository;
import com.nc.g49_smartcrm.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ContactRepository contactRepository;

    // Conversaciones
    @Override
    public Long countConversations(Long userId) {
        return conversationRepository.countConversationsByUser_Id(userId);
    }

    @Override
    public List<ChannelCountDto> countConversationsByChannels(Long userId) {
        return conversationRepository.countConversationsByChannelForUser(userId);
    }

    @Override
    public Long countOpenConversations(Long userId) {
        return conversationRepository.countByUser_IdAndStatus(userId, ConversationStatus.OPEN);
    }

    @Override
    public Long countClosedConversations(Long userId) {
        return conversationRepository.countByUser_IdAndStatus(userId, ConversationStatus.CLOSED);
    }
    @Override
    public List<CountPerDayDto> countConversationsCreatedPerDay(Long userId) {
        return conversationRepository.countConversationsCreatedPerDay(userId);
    }
    @Override
    public Double avgResolutionTime(Long userId) {
        return conversationRepository.avgResolutionTimeForUser(userId);
    }

    // Mensajes
    @Override
    public Long countMessagesSent(Long userId) {
        return messageRepository.countMessagesByDirectionAndUser(userId, MessageDirection.OUTBOUND);
    }

    @Override
    public Long countMessagesReceived(Long userId) {
        return messageRepository.countMessagesByDirectionAndUser(userId, MessageDirection.INBOUND);
    }

    @Override
    public List<ChannelCountDto>countMessageByChannel(Long userId){
        return messageRepository.countMessagesByChannelForUser(userId);
    }

    @Override
    public List<CountPerDayDto>countMessagePerDayByUser(Long userId){
        return messageRepository.countMessagesPerDayByUser(userId);
    }

    // Contactos
    @Override
    public Long countContactsByStatus(Long userId, ContactStatus status) {
        return contactRepository.countByOwner_IdAndStatus(userId, status);
    }

    @Override
    public List<SourceCountDto> countContactsBySource(Long userId) {
        return contactRepository.countContactsBySourceForUser(userId);
    }
    @Override
    public List<CountPerDayDto> countContactsPerDay(Long userId) {
        return contactRepository.countContactsPerDayForUser(userId);
    }


}
