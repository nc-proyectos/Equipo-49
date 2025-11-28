package com.nc.g49_smartcrm.exception;

public abstract class NotFoundException extends ApiException {
    protected NotFoundException(String message) {
        super(message);
    }
}
