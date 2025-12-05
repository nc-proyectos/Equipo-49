package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private Long id;
    private Long userId;
    private String contactName;
    private String message;
    private Instant reminderAt;
    private Task.Status status;
}


