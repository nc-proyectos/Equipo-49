package com.nc.g49_smartcrm.dto;

import com.nc.g49_smartcrm.model.ContactSource;
import com.nc.g49_smartcrm.model.ContactStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "Firstname is required")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    private String lastname;

    @Email(message = "Email must be valid")
    private String email;

    private String phone;

    private ContactStatus status;

    private ContactSource source;

    private Long ownerId;
}
