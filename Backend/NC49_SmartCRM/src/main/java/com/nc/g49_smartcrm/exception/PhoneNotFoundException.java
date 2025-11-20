package com.nc.g49_smartcrm.exception;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(String phone) {
        super("Contact with phone " + phone + " not found");
    }
}
