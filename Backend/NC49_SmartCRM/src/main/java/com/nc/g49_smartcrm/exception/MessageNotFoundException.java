package com.nc.g49_smartcrm.exception;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException() {
        super();
    }

    public MessageNotFoundException(String message) {
        super(message);
    }

    public MessageNotFoundException(Long id) {
        super("Message with id " + id + " not found");
    }
}
