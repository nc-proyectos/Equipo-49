package com.nc.g49_smartcrm.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

        System.out.println("📩 Mensaje recibido de " + from);
        System.out.println("Contenido: " + body);
        System.out.println("Message SID: " + messageId);
        //todo: que guarde en la bdd el contacto si es el 1er mensaje.

        //todo: que guarde el mensaje en la bdd.

        return "Mensaje recibido";
    }
}
