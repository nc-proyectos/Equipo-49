package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.model.Notification;
import com.nc.g49_smartcrm.repository.NotificationRepository;
import com.nc.g49_smartcrm.websocket.NotificationWebSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationWebSocketHandler wsHandler;
    private final NotificationRepository notificationRepository;
    private final Logger logger = Logger.getLogger(NotificationServiceImpl.class.getName());

    public void sendReminder(Long userId, Long taskId, String message) {

        logger.info("Sending reminder for taskId= {}");
        Notification notification = new Notification(
                userId,
                "TASK_REMINDER",
                message,
                taskId
        );

        notification = notificationRepository.save(notification);
        boolean sent = wsHandler.sendToUser(userId, notification);

        if (!sent) {
            logger.warning("User offline, notification stored as unread");
        }
    }
}

