package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.Channel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConversationStartRequest {

    @NotNull
    private Long userId;

    private String subject;

    @NotNull(message = "contactId is required")
    private Long contactId;

    @NotBlank(message = "channel is required")
    private Channel channel;

    @NotBlank(message = "firstMessage is required")
    private String firstMessage;
}
