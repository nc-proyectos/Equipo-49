package com.nc.g49_smartcrm.config;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class WebSocketAuthInterceptor implements HandshakeInterceptor {

    private static final Logger log = LoggerFactory.getLogger(WebSocketAuthInterceptor.class);

    @Override
    public boolean beforeHandshake(
            @Nullable ServerHttpRequest request,
            @Nullable ServerHttpResponse response,
            @NonNull WebSocketHandler wsHandler,
            @NonNull Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest servletRequest) {

            String userId = servletRequest.getServletRequest().getParameter("userId");

            if (userId == null) {
                log.warn("WebSocket connection rejected: missing userId");
                return false;
            }

            log.info("WebSocket handshake accepted for user {}", userId);
            attributes.put("userId", Long.parseLong(userId));
        }

        return true;
    }

    @Override
    public void afterHandshake(
            @Nullable org.springframework.http.server.ServerHttpRequest request,
            @Nullable org.springframework.http.server.ServerHttpResponse response,
            @Nullable WebSocketHandler wsHandler,
            Exception exception) {
        //TODO Complete method
    }
}