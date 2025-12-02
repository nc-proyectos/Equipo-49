package com.nc.g49_smartcrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Integration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo de canal: WHATSAPP, EMAIL, INSTAGRAM, TELEGRAM, ETC
    @Enumerated(EnumType.STRING)
    private Channel type;

    // Identificador principal del canal (puede ser email, phone_number_id, page_id, bot_id, etc)
    private String externalId;

    // Nombre legible para el usuario (ej: "+5491122334455", "soporte@empresa.com")
    private String displayName;

    // Usado por APIs OAuth o tokens de WhatsApp
    @Column(columnDefinition = "TEXT")
    private String accessToken;

    // Datos variables específicos de cada proveedor
    @Lob
    private String extraData; // JSON dinámico, flexible, lo que quieras.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean active = true;
}
