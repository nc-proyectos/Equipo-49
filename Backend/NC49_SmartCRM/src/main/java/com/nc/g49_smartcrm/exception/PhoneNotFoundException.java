package com.nc.g49_smartcrm.exception;

public class PhoneNotFoundException extends NotFoundException {
    public PhoneNotFoundException(String phone) {
        super("Contact with phone " + phone + " not found");
    }
}
