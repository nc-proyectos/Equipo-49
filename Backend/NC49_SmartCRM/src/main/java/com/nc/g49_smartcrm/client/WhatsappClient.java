package com.nc.g49_smartcrm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "whatsapp", url = "${whatsapp.api.url}")
public interface WhatsappClient {

    @PostMapping("/{phoneNumberId}/messages")
    String sendMessage(
            @PathVariable String phoneNumberId,
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> body
    );
}
