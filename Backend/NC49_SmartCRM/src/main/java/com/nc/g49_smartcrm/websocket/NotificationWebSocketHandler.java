package com.nc.g49_smartcrm.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(NotificationWebSocketHandler.class);

    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    public NotificationWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        if (userId != null) {
            sessions.put(userId, session);
            log.info("WebSocket connected: user {}", userId);
        } else {
            log.warn("WebSocket connected without userId attribute, closing session");
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(
            @NonNull WebSocketSession session,
            @NonNull CloseStatus status) {
        Long userId = (Long) session.getAttributes().get("userId");
        if (userId != null) {
            sessions.remove(userId);
            log.info("WebSocket disconnected: user {}", userId);
        } else {
            log.warn("WebSocket disconnected without userId attribute, closing session");
        }
    }

    public boolean sendToUser(Long userId, Object payload) {

        log.info("[SEND TO USER] Sending notification to user {}", userId);
        WebSocketSession session = sessions.get(userId);

        if (session == null || !session.isOpen()) {
            log.warn("User {} not connected or session closed, cannot send WS", userId);
            return false;
        }

        try {
            String json;
            if (payload instanceof String string) {
                json = string;
            } else {
                json = objectMapper.writeValueAsString(payload);
            }

            TextMessage msg = new TextMessage(json);
            session.sendMessage(msg);

            log.info("Sent to user {}: {}", userId, json);
            return true;

        } catch (Exception e) {
            log.error("Error sending WebSocket message to user {}", userId, e);
            return false;
        }
    }
}