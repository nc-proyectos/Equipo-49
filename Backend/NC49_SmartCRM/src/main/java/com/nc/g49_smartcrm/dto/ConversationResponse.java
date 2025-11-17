package com.nc.g49_smartcrm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConversationResponse {

    private Long id;
    private Long contactId;
    private Long ownerId;
    private String status;
    private Instant startedAt;
    private Instant closedAt;
}
