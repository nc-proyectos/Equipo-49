package com.nc.g49_smartcrm.exception;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException() {
        super();
    }

    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException(Long id) {
        super("Contact with id " + id + " not found");
    }
}
