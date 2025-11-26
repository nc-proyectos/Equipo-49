package com.nc.g49_smartcrm.exception;

public class TaskNotFoundException extends ResourceNotFoundException {

    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " not found");
    }
}
