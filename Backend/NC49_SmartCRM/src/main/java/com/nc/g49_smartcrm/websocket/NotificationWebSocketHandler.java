package com.nc.g49_smartcrm.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(NotificationWebSocketHandler.class);

    // map userId -> session
    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = extractUserId(session.getUri());

        if (userId == null) {
            log.warn("WS connection without userId, closing");
            session.close();
            return;
        }

        sessions.put(userId, session);
        log.info("WS connected for userId={}", userId);
    }

    private Long extractUserId(URI uri) {
        if (uri == null || uri.getQuery() == null) {
            return null;
        }

        return Arrays.stream(uri.getQuery().split("&"))
                .map(kv -> kv.split("="))
                .filter(parts -> parts.length == 2 && "userId".equals(parts[0]))
                .findFirst()
                .map(parts -> {
                    try {
                        return Long.parseLong(parts[1]);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .orElse(null);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.values().removeIf(s -> s.getId().equals(session.getId()));
        log.info("WS session closed: {}", session.getId());
    }

    public void sendToUser(Long userId, TextMessage msg) throws IOException {
        WebSocketSession s = sessions.get(userId);

        if (s != null && s.isOpen()) {
            s.sendMessage(msg);
        } else {
            log.warn("No WS session for userId={}", userId);
        }
    }
}