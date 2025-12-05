package com.nc.g49_smartcrm.exception;

public class MessageNotFoundException extends NotFoundException {
    public MessageNotFoundException(Long id) {
        super("Message with id " + id + " not found");
    }
}
