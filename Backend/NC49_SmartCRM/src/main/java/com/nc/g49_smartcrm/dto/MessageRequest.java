package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.SenderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRequest {
    @NotNull
    private Long senderId;

    @NotNull
    private SenderType senderType;

    @NotBlank(message = "Message content is required")
    private String body;
}
