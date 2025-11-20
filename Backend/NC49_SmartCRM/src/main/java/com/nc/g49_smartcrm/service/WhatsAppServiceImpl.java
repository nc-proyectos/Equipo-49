package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.*;
import com.nc.g49_smartcrm.model.Channel;
import com.nc.g49_smartcrm.model.ContactSource;
import com.nc.g49_smartcrm.model.ContactStatus;
import com.nc.g49_smartcrm.model.SenderType;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WhatsAppServiceImpl implements WhatsAppService{
    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    private final MessageService messageService;

    public void enviarMensaje(String numeroDestino,String mensaje){
        //agregar el parametro userId si es que usamos varios users y agregar metodo q busque el user por el id.
        Message.creator(
                new PhoneNumber("whatsapp:"+numeroDestino),
                new PhoneNumber("whatsapp:"+twilioPhoneNumber),
                mensaje
        ).create();
        messageService.saveMessage(new InboundMessage(
                Channel.WHATSAPP,
                ContactSource.WHATSAPP,
                SenderType.AGENT,
                null,
                null,
                numeroDestino,
                null,
                mensaje
        ));
    }

    public String recibirMensaje(Map<String, String> datos){
        String phone = datos.get("From");        // +54911.... (cliente)
        String message = datos.get("Body");        // mensaje del cliente

        phone=phone.replace("whatsapp:", "");

        messageService.saveMessage(new InboundMessage(
                Channel.WHATSAPP,
                ContactSource.WHATSAPP,
                SenderType.CONTACT,
                null,
                null,
                phone,
                null,
                message
        ));

        return "Mensaje recibido";
    }
}
