package com.nc.g49_smartcrm.exception;

public class ConversationAlreadyClosedException extends RuntimeException {
    public ConversationAlreadyClosedException(Long id) {
        super("Conversation " + id + " is already closed");
    }
}
