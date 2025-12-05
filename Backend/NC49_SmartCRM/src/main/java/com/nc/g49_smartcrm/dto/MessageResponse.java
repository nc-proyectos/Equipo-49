package com.nc.g49_smartcrm.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageResponse {

    private Long id;
    private String content;
    private SenderResponse sender;
    private Instant createdAt;
}
