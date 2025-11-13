package com.nc.g49_smartcrm.exception;

public class ConversationNotFoundException extends RuntimeException {

    public ConversationNotFoundException() {
        super();
    }

    public ConversationNotFoundException(String message) {
        super(message);
    }

    public ConversationNotFoundException(Long id) {
        super("Conversation with id " + id + " not found");
    }
}
