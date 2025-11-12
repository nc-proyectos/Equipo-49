package com.crishof.nc49_smartcrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_messages")
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Conversation conversation;

    @Enumerated(EnumType.STRING)
    private SenderType senderType;

    private Long senderId;

    @Lob
    private String body;

    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    private Instant createdAt;
}
