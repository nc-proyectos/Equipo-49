package com.nc.g49_smartcrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String type;

    @Column(length = 500)
    private String message;

    private Long relatedId; // para taskId, mensajeId, etc.

    private boolean read;

    private LocalDateTime createdAt;

    public Notification(Long userId, String type, String message, Long relatedId) {
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.relatedId = relatedId;
        this.read = false;
        this.createdAt = LocalDateTime.now();
    }
}
