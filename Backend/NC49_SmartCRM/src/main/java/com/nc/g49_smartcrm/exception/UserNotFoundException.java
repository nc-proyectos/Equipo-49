package com.nc.g49_smartcrm.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
