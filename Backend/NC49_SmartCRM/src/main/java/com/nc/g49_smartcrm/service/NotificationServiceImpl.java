package com.nc.g49_smartcrm.service;


import com.nc.g49_smartcrm.websocket.NotificationWebSocketHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationWebSocketHandler wsHandler;

    @Override
    public void sendReminder(Long userId, Long taskId, String message) {
        String payload = String.format("{\"type\":\"TASK_REMINDER\",\"taskId\":%d,\"message\":\"%s\"}", taskId, message.replace("\"", "\\\""));
        try {
            wsHandler.sendToUser(userId, new TextMessage(payload));
            log.info("Reminder sent to user {}: {}", userId, payload);
        } catch (Exception e) {
            log.error("Error sending ws to user {}", userId, e);
        }
    }
}

