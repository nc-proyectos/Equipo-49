package com.nc.g49_smartcrm.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageRequest {

    @NotBlank(message = "Message content is required")
    private String content;

    @NotBlank(message = "Sender is required")
    private String sender;
}
