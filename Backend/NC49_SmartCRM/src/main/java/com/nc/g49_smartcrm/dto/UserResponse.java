package com.nc.g49_smartcrm.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private Instant createdAt;
    private Instant updatedAt;
}
