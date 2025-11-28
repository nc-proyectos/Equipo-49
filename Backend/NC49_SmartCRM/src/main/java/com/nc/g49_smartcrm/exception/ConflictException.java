package com.nc.g49_smartcrm.exception;

public abstract class ConflictException extends ApiException {
    protected ConflictException(String message) {
        super(message);
    }
}
