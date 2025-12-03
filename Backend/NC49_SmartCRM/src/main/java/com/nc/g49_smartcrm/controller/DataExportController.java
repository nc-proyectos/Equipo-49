package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.service.DataExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-export")
@RequiredArgsConstructor
@Tag(name = "Data export", description = "End points para exportar datos")
public class DataExportController {

    private final DataExportService dataExportService;

    @Operation(summary = "Crear pdf con mensajes de una conversacion")
    @GetMapping("/conversations/{conversationId}")
    @ResponseStatus(HttpStatus.OK)
    public void exportConversation(@PathVariable("conversationId") Long conversationId, HttpServletResponse response) throws Exception{
        dataExportService.exportConversationPdf(conversationId, response);
    }

    @Operation(summary = "Crear pdf con contactos de un usuario")
    @GetMapping("/contacts/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void exportContacts(@PathVariable("userId")Long userId, HttpServletResponse response) throws Exception{
        dataExportService.exportContacts(userId, response);
    }

    @Operation(summary = "Crear pdf con metricas de un usuario")
    @GetMapping("/metrics/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void exportMetricsPdf(@PathVariable("userId")Long userId, HttpServletResponse response) throws Exception{
        dataExportService.exportMetricsPdf(userId, response);
    }
}
