package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.service.MessageService;
import com.nc.g49_smartcrm.service.WhatsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final WhatsAppService whatsAppService;
    private final MessageService messageService;

    //end point para que twilio envie los mensajes y el backend los reciba.
    @PostMapping("/whatsapp")
    @ResponseStatus(HttpStatus.OK)
    public String recibirMensaje(@RequestParam Map<String, String> datos){
        return whatsAppService.recibirMensaje(datos);
    }

    //end point para responder al cliente por whatsapp.
    @PostMapping("/responder")
    @ResponseStatus(HttpStatus.OK)
    public String enviarMensaje(@RequestParam String numero, @RequestParam String mensaje){
        whatsAppService.enviarMensaje(numero,mensaje);
        return "Mensaje enviado con exito";
    }

    @GetMapping("/getById/{id}")
    public MessageResponse getById(@PathVariable Long id) {
        return messageService.getById(id);
    }
}
