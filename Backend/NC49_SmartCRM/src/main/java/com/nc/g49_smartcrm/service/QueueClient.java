package com.nc.g49_smartcrm.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class QueueClient {

    private final RestTemplate restTemplate;

    @Value("${WORKER_BASE_URL}")
    private String workerBaseUrl;

    public void enqueueReminder(Long taskId, Long userId, Instant when) {

        try {

            //TODO set USER
            userId = 1L;
            URI uri = URI.create(workerBaseUrl + "/queue/reminder");

            Map<String, Object> payload = Map.of(
                    "taskId", taskId,
                    "userId", userId,
                    "reminderAt", when.toString()
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

            log.info("Enqueuing job: {}", payload);

            restTemplate.postForEntity(uri, entity, Void.class);

            log.info("Job send to Worker Node");


        } catch (Exception ex) {
            log.error("Error sending job to Worker Node", ex);
        }
    }
}





