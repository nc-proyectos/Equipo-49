package com.nc.g49_smartcrm.exception;

public abstract class UnauthorizedException extends ApiException {
    protected UnauthorizedException(String message) {
        super(message);
    }
}
