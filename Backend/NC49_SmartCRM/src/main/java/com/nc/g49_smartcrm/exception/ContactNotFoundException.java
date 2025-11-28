package com.nc.g49_smartcrm.exception;

public class ContactNotFoundException extends NotFoundException {
    public ContactNotFoundException(Long id) {
        super("Contact with id " + id + " not found");
    }
}
