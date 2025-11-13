package com.nc.g49_smartcrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private ContactStatus contactStatus;

    @Enumerated(EnumType.STRING)
    private ContactSource contactSource;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    private Instant createdAt;
    private Instant updatedAt;
}
