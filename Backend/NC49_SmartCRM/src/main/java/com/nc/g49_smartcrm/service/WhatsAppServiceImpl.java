package com.nc.g49_smartcrm.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.nc.g49_smartcrm.client.WhatsappClient;
import com.nc.g49_smartcrm.dto.*;
import com.nc.g49_smartcrm.model.Channel;
import com.nc.g49_smartcrm.model.ContactSource;
import com.nc.g49_smartcrm.model.SenderType;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class WhatsAppServiceImpl implements WhatsAppService {
    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public void enviarMensaje(String numeroDestino, String mensaje) {
        Message.creator(
                new PhoneNumber("whatsapp:" + numeroDestino),
                new PhoneNumber("whatsapp:" + twilioPhoneNumber),
                mensaje
        ).create();
    }

    public String recibirMensaje(Map<String, String> datos) {
        String from = datos.get("From");        // +54911.... (cliente)
        String body = datos.get("Body");        // mensaje del cliente
        String messageId = datos.get("MessageSid");

            WhatsAppWebhook.Message msg = messages.get(0);

            String from = "+" + msg.getFrom();
            String body = msg.getText().getBody();

            System.out.println("From: " + from);
            System.out.println("Msg: " + body);

            messageService.saveMessage(new InboundMessage(
                    Channel.WHATSAPP,
                    ContactSource.WHATSAPP,
                    SenderType.CONTACT,
                    null,
                    null,
                    from,
                    null,
                    body,
                    1L
            ));

            System.out.println("mensaje recibido!");

        } catch (Exception e) {
            System.out.println("⚠ Error procesando webhook: " + e.getMessage());
        }
    }

    public String verifyUrl(String mode,String challenge,String token){
        if (!("subscribe".equals(mode) && "123".equals(token))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Invalid verify token");
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
