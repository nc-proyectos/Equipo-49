package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.dto.SourceCountDto;
import com.nc.g49_smartcrm.model.ContactStatus;

import java.util.List;

public interface MetricsService {
    Long countConversations(Long userId);

    List<ChannelCountDto> countConversationsByChannels(Long userId);

    Long countOpenConversations(Long userId);

    Long countClosedConversations(Long userId);

    List<CountPerDayDto> countConversationsCreatedPerDay(Long userId);

    Double avgResolutionTime(Long userId);

    List<CountPerDayDto> countContactsPerDay(Long userId);

    List<ChannelCountDto> countMessageByChannel(Long userId);

    List<CountPerDayDto> countMessagePerDayByUser(Long userId);

    Long countMessagesSent(Long userId);

    Long countMessagesReceived(Long userId);

    Long countContactsByStatus(Long userId, ContactStatus status);

    List<SourceCountDto> countContactsBySource(Long userId);
}
