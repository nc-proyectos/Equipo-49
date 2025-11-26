package com.nc.g49_smartcrm.service;

public interface NotificationService {
    void sendReminder(Long userId, Long taskId, String message);
}
