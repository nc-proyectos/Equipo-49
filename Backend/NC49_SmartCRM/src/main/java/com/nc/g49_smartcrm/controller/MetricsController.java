package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.CountPerDayDto;
import com.nc.g49_smartcrm.dto.SourceCountDto;
import com.nc.g49_smartcrm.model.ContactStatus;
import com.nc.g49_smartcrm.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metric")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "End points para metricas/estadisticas")
public class MetricsController {

    private final MetricsService metricsService;

    // ==========================
    //        CONVERSACIONES
    // ==========================

    @Operation(summary = "Cuenta conversaciones totales de un user/dueño.")
    @GetMapping("/conversations/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countConversations(@RequestParam Long userId) {
        return metricsService.countConversations(userId);
    }

    @Operation(summary = "Cuenta conversaciones totales por canal(wpp, mail, etc) de un user/dueño.")
    @GetMapping("/conversations/by-channel")
    @ResponseStatus(HttpStatus.OK)
    public List<ChannelCountDto> countConversationsByChannel(@RequestParam Long userId) {
        return metricsService.countConversationsByChannels(userId);
    }

    @Operation(summary = "Cuenta conversaciones abiertas totales de un user/dueño.")
    @GetMapping("/conversations/open")
    @ResponseStatus(HttpStatus.OK)
    public Long countOpenConversations(@RequestParam Long userId) {
        return metricsService.countOpenConversations(userId);
    }

    @Operation(summary = "Cuenta conversaciones cerradas totales de un user/dueño.")
    @GetMapping("/conversations/closed")
    @ResponseStatus(HttpStatus.OK)
    public Long countClosedConversations(@RequestParam Long userId) {
        return metricsService.countClosedConversations(userId);
    }

    @Operation(summary = "Cuenta conversaciones totales por dia de un user/dueño.")
    @GetMapping("/conversations/per-day")
    @ResponseStatus(HttpStatus.OK)
    public List<CountPerDayDto> countConversationsPerDay(@RequestParam Long userId) {
        return metricsService.countConversationsCreatedPerDay(userId);
    }

    @Operation(summary = "Promedio de resolucion de conversaciones de un user/dueño.")
    @GetMapping("/conversations/avg-resolution-time")
    @ResponseStatus(HttpStatus.OK)
    public Double avgResolutionTime(@RequestParam Long userId) {
        return metricsService.avgResolutionTime(userId);
    }


    // ==========================
    //          MENSAJES
    // ==========================

    @Operation(summary = "Cuenta mensajes enviados totales de un user/dueño.")
    @GetMapping("/messages/sent")
    @ResponseStatus(HttpStatus.OK)
    public Long countMessagesSent(@RequestParam Long userId) {
        return metricsService.countMessagesSent(userId);
    }

    @Operation(summary = "Cuenta mensajes recibidos totales de un user/dueño.")
    @GetMapping("/messages/received")
    @ResponseStatus(HttpStatus.OK)
    public Long countMessagesReceived(@RequestParam Long userId) {
        return metricsService.countMessagesReceived(userId);
    }

    @Operation(summary = "Cuenta mensajes totales por canal de un user/dueño.")
    @GetMapping("/messages/by-channel")
    @ResponseStatus(HttpStatus.OK)
    public List<ChannelCountDto> countMessagesByChannel(@RequestParam Long userId) {
        return metricsService.countMessageByChannel(userId);
    }

    @Operation(summary = "Cuenta mensajes totales por dia de un user/dueño.")
    @GetMapping("/messages/per-day")
    @ResponseStatus(HttpStatus.OK)
    public List<CountPerDayDto> countMessagesPerDay(@RequestParam Long userId) {
        return metricsService.countMessagePerDayByUser(userId);
    }


    // ==========================
    //          CONTACTOS
    // ==========================

    @Operation(summary = "Cuenta contactos totales por estado(LEAD_ACTIVE, IN_FOLLOW_UP o CLIENT) de un user/dueño.")
    @GetMapping("/contacts/by-status")
    @ResponseStatus(HttpStatus.OK)
    public Long countContactsByStatus(
            @RequestParam Long userId,
            @RequestParam ContactStatus status
    ) {
        return metricsService.countContactsByStatus(userId, status);
    }

    @Operation(summary = "Cuenta contactos totales por source(wpp, mail, etc) de un user/dueño.")
    @GetMapping("/contacts/by-source")
    @ResponseStatus(HttpStatus.OK)
    public List<SourceCountDto> countContactsBySource(@RequestParam Long userId) {
        return metricsService.countContactsBySource(userId);
    }

    @Operation(summary = "Cuenta contactos totales por dia(o sea contactos nuevos) de un user/dueño.")
    @GetMapping("/contacts/per-day")
    @ResponseStatus(HttpStatus.OK)
    public List<CountPerDayDto> countContactsPerDay(@RequestParam Long userId) {
        return metricsService.countContactsPerDay(userId);
    }

}
