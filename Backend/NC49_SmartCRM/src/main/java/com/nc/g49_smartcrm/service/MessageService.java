package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.InboundMessage;
import com.nc.g49_smartcrm.dto.MessageRequest;
import com.nc.g49_smartcrm.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse getById(Long id);

    MessageResponse addMessage(Long conversationId, MessageRequest request);

    List<MessageResponse> getMessages(Long conversationId);

    MessageResponse saveMessage(InboundMessage inboundMessage);
}
