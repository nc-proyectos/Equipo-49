package com.nc.g49_smartcrm.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.nc.g49_smartcrm.client.WhatsappClient;
import com.nc.g49_smartcrm.dto.DataMessage;
import com.nc.g49_smartcrm.dto.WhatsAppWebhook;
import com.nc.g49_smartcrm.exception.UserNotFoundException;
import com.nc.g49_smartcrm.model.*;
import com.nc.g49_smartcrm.repository.IntegrationRepository;
import com.nc.g49_smartcrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WhatsAppServiceImpl implements WhatsAppService {
    private final WhatsappClient client;
    private final MessageService messageService;
    private final IntegrationRepository integrationRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${whatsapp.api.url}")
    private String baseUrl;
    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;
    @Value("${whatsapp.token}")
    private String token;

    public void sendTextMessage(String to, String message) throws NumberParseException {
        Map<String, Object> body = Map.of(
                "messaging_product", "whatsapp",
                "recipient_type", "individual",
                "to", to,
                "type", "text",
                "text", Map.of("body", message)
        );

        User user=userService.getCurrentUser();

        Integration integration=integrationRepository.findByTypeAndUserId(Channel.WHATSAPP, user.getId())
                        .orElseThrow(()->new RuntimeException("Integration id not found."));

        client.sendMessage(
                integration.getExternalId(),
                "Bearer " + integration.getAccessToken(),
                body
        );

        to = normalizePhone(to);
        messageService.saveMessage(new DataMessage(
                Channel.WHATSAPP,
                ContactSource.WHATSAPP,
                SenderType.AGENT,
                null,
                null,
                to,
                null,
                message,
                user.getId()
        ));
    }

    public void receiveMessage(WhatsAppWebhook webhook) {
        //esto tiene q estar asi si no tira error 500 en ngrok
        try {
            // seguridad: entry
            if (webhook.getEntry() == null || webhook.getEntry().isEmpty()) {
                return;
            }

            WhatsAppWebhook.Entry entry = webhook.getEntry().get(0);

            // seguridad: changes
            if (entry.getChanges() == null || entry.getChanges().isEmpty()) {
                return;
            }

            WhatsAppWebhook.Change change = entry.getChanges().get(0);

            if (change.getValue() == null) {
                return;
            }

            // seguridad: messages
            List<WhatsAppWebhook.Message> messages = change.getValue().getMessages();

            if (messages == null || messages.isEmpty()) {
                // no son mensajes → puede ser un "status"
                return;
            }
            WhatsAppWebhook.Message msg = messages.get(0);

            String userPhoneId=change.getValue().getMetadata().getPhone_number_id();

            Integration integration=integrationRepository.findByExternalId(userPhoneId)
                    .orElseThrow(()->new RuntimeException("User phone id not found"));

            String from = "+" + msg.getFrom();
            String body = msg.getText().getBody();

            System.out.println("From: " + from);
            System.out.println("Msg: " + body);

            messageService.saveMessage(new DataMessage(
                    Channel.WHATSAPP,
                    ContactSource.WHATSAPP,
                    SenderType.CONTACT,
                    null,
                    null,
                    from,
                    null,
                    body,
                    integration.getUser().getId()
            ));

            System.out.println("mensaje recibido!");

        } catch (Exception e) {
            System.out.println("⚠ Error procesando webhook: " + e.getMessage());
        }
    }

    public String verifyUrl(String mode, String challenge, String token) {
        if (!("subscribe".equals(mode) && "123".equals(token))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid verify token");
        }
        return challenge;
    }

    private String normalizePhone(String raw) throws NumberParseException {
        PhoneNumberUtil util = PhoneNumberUtil.getInstance();

        // Intenta parsear el número (Argentina como fallback si no se sabe)
        Phonenumber.PhoneNumber number = util.parse(raw, "AR");

        // Si el número es válido  devolver en E.164
        if (util.isValidNumber(number)) {
            return util.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
        }

        // fallback simple en caso de error
        return raw.replaceAll("[^0-9]", "");
    }

}

