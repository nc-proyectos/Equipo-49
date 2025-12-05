package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.ContactSource;
import com.nc.g49_smartcrm.model.ContactStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ContactResponse {

    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    private ContactStatus status;
    private ContactSource source;

    private Long ownerId;
    private String ownerName;

    private Instant createdAt;
    private Instant updatedAt;
}
