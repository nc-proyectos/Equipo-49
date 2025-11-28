package com.nc.g49_smartcrm.exception;

public abstract class BadRequestException extends ApiException {
    protected BadRequestException(String message) {
        super(message);
    }
}
