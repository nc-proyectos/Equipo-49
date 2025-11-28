package com.nc.g49_smartcrm.exception;

public class ConversationNotFoundException extends NotFoundException {
    public ConversationNotFoundException(Long id) {
        super("Conversation with id " + id + " not found");
    }
}
