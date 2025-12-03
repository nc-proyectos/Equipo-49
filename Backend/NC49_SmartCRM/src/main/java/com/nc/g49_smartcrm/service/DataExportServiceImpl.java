package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ChannelCountDto;
import com.nc.g49_smartcrm.dto.ContactResponse;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.dto.SourceCountDto;
import com.nc.g49_smartcrm.model.ContactStatus;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataExportServiceImpl implements DataExportService {

    private final MessageService messageService;
    private final ContactService contactService;
    private final MetricsService metricsService;
    private final PdfWriterService pdfWriterService;

    private String formatDate(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.of("America/Argentina/Buenos_Aires"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public void exportConversationPdf(Long conversationId, HttpServletResponse response) throws Exception {
        List<MessageResponse> messages = messageService.getMessages(conversationId);

        pdfWriterService.createPdf(response, "conversation.pdf", (stream, y) -> {

            // TÍTULO
            pdfWriterService.writeLine(stream, "CONVERSACIÓN #" + conversationId, y);
            y -= 20;

            pdfWriterService.writeLine(stream, "------------------------------------------", y);
            y -= 30;

            // CUERPO DEL CHAT
            for (MessageResponse m : messages) {

                String line = "[" + formatDate(m.getCreatedAt()) + "] "
                        + m.getSender().firstName() + " "
                        + m.getSender().lastName() + ": "
                        + m.getBody();

                pdfWriterService.writeLine(stream, line, y);
                y -= 20;

                if (y < 50) return; // lo maneja createPdf()
            }
        });
    }
    @Override
    public void exportContacts(Long userId, HttpServletResponse response) throws Exception {
        List<ContactResponse> contacts = contactService.findAllContactsByOwnerId(userId);

        pdfWriterService.createPdf(response, "contacts.pdf", (stream, y) -> {

            // TÍTULO
            pdfWriterService.writeLine(stream, "REPORTE DE CONTACTOS - Usuario " + userId, y);
            y -= 20;

            pdfWriterService.writeLine(stream, "------------------------------------------", y);
            y -= 30;

            for (ContactResponse c : contacts) {

                pdfWriterService.writeLine(stream, "• " + c.getFirstname() + " " + c.getLastname(), y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Email: " + c.getEmail(), y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Teléfono: " + c.getPhone(), y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Estado: " + c.getStatus(), y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Fuente: " + c.getSource(), y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Owner: " + c.getOwnerName() + " (ID: " + c.getOwnerId() + ")", y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Creado: " + formatDate(c.getCreatedAt()), y);
                y -= 15;

                pdfWriterService.writeLine(stream, "   Actualizado: " + formatDate(c.getUpdatedAt()), y);
                y -= 25;

                pdfWriterService.writeLine(stream, "------------------------------------------", y);
                y -= 25;

                if (y < 80) return; // salto automático manejado por createPdf()

            }
        });
    }
    @Override
    public void exportMetricsPdf(Long userId, HttpServletResponse response) throws Exception {

        pdfWriterService.createPdf(response, "metrics_report.pdf", (stream, y) -> {

            // TITULO PRINCIPAL
            pdfWriterService.writeLine(stream, "INFORME DE MÉTRICAS - Usuario " + userId, y);
            y -= 20;

            // LINEA SEPARADORA
            pdfWriterService.writeLine(stream, "------------------------------------------", y);
            y -= 30;


            // ============================
            // 1. RESUMEN GENERAL
            // ============================
            pdfWriterService.writeLine(stream, "1. Resumen General", y);
            y -= 20;

            pdfWriterService.writeLine(stream, "   • Conversaciones Totales: " + metricsService.countConversations(userId), y);
            y -= 20;

            pdfWriterService.writeLine(stream, "   • Conversaciones Abiertas: " + metricsService.countOpenConversations(userId), y);
            y -= 20;

            pdfWriterService.writeLine(stream, "   • Conversaciones Cerradas: " + metricsService.countClosedConversations(userId), y);
            y -= 20;

            pdfWriterService.writeLine(
                    stream,
                    "   • Tiempo promedio de resolución: " +
                            (metricsService.avgResolutionTime(userId) == null ? "-" : metricsService.avgResolutionTime(userId) + " hs"),
                    y
            );
            y -= 40;


            // ============================
            // 2. ACTIVIDAD DE MENSAJES
            // ============================
            pdfWriterService.writeLine(stream, "2. Actividad de Mensajes", y);
            y -= 20;

            pdfWriterService.writeLine(stream, "   • Enviados: " + metricsService.countMessagesSent(userId), y);
            y -= 20;

            pdfWriterService.writeLine(stream, "   • Recibidos: " + metricsService.countMessagesReceived(userId), y);
            y -= 40;


            // ============================
            // 3. CONTACTOS POR ESTADO
            // ============================
            pdfWriterService.writeLine(stream, "3. Contactos por Estado", y);
            y -= 20;

            for (ContactStatus status : ContactStatus.values()) {
                Long count = metricsService.countContactsByStatus(userId, status);
                pdfWriterService.writeLine(stream, "   • " + status + ": " + count, y);
                y -= 20;
            }
            y -= 20;


            // ============================
            // 4. CONTACTOS POR FUENTE
            // ============================
            pdfWriterService.writeLine(stream, "4. Contactos por Fuente", y);
            y -= 20;

            List<SourceCountDto> sources = metricsService.countContactsBySource(userId);
            for (SourceCountDto s : sources) {
                pdfWriterService.writeLine(stream, "   • " + s.source() + ": " + s.count(), y);
                y -= 20;
            }
            y -= 20;


            // ============================
            // 5. CONVERSACIONES POR CANAL
            // ============================
            pdfWriterService.writeLine(stream, "5. Conversaciones por Canal", y);
            y -= 20;

            for (ChannelCountDto c : metricsService.countConversationsByChannels(userId)) {
                pdfWriterService.writeLine(stream, "   • " + c.channel() + ": " + c.count(), y);
                y -= 20;
            }
            y -= 20;


            // ============================
            // 6. MENSAJES POR CANAL
            // ============================
            pdfWriterService.writeLine(stream, "6. Mensajes por Canal", y);
            y -= 20;

            for (ChannelCountDto c : metricsService.countMessageByChannel(userId)) {
                pdfWriterService.writeLine(stream, "   • " + c.channel() + ": " + c.count(), y);
                y -= 20;
            }
        });
    }

}
