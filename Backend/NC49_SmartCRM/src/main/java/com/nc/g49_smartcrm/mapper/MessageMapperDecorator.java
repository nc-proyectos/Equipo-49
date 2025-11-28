package com.nc.g49_smartcrm.mapper;

import com.nc.g49_smartcrm.dto.MessageRequest;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.dto.SenderResponse;
import com.nc.g49_smartcrm.model.Message;
import com.nc.g49_smartcrm.model.SenderType;
import com.nc.g49_smartcrm.service.ContactService;
import com.nc.g49_smartcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MessageMapperDecorator implements MessageMapper {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    private MessageMapper delegate;

    @Autowired
    public void setDelegate(MessageMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public MessageResponse toDto(Message message) {
        MessageResponse messageResponse = delegate.toDto(message);
        SenderResponse senderResponse;

        if (message.getSenderType().equals(SenderType.CONTACT)) {
            var contacto = contactService.getById(message.getSenderId());
            senderResponse = new SenderResponse(
                    message.getSenderType(),
                    contacto.getId(),
                    contacto.getFirstname(),
                    contacto.getLastname(),
                    contacto.getPhone(),
                    contacto.getEmail()
            );
        } else {
            var user = userService.findById(message.getSenderId());
            senderResponse = new SenderResponse(
                    message.getSenderType(),
                    user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    null,
                    user.getEmail()
            );
        }
        messageResponse.setSender(senderResponse);
        return messageResponse;

    }

    @Override
    public Message toEntity(MessageRequest messageRequest) {
        return delegate.toEntity(messageRequest);
    }
}
