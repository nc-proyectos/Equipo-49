package com.crishof.nc49_smartcrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tbl_conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contact contact;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    private Instant lastMessageAt;

    private int unreadMessages = 0;

    @OneToMany
    private List<Message> messages;

}
