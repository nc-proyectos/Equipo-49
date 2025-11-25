package com.nc.g49_smartcrm.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.dto.WhatsAppWebhook;
import com.nc.g49_smartcrm.service.MessageService;
import com.nc.g49_smartcrm.service.WhatsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final WhatsAppService whatsAppService;
    private final MessageService messageService;

    //la api de wpp verifica la url a la q le va a mandar los enventos.(en este caso mensajes)
    @GetMapping("/whatsapp")
    @ResponseStatus(HttpStatus.OK)
    public String verifyUrl(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.challenge") String challenge,
            @RequestParam(name = "hub.verify_token") String token) {

        return whatsAppService.verifyUrl(mode,challenge,token);
    }

    //el end point q recibe los mensajes desde la api de wpp.
    @PostMapping("/whatsapp")
    @ResponseStatus(HttpStatus.OK)
    public void receiveMessage(@RequestBody WhatsAppWebhook webhook) {
        whatsAppService.receiveMessage(webhook);
    }

    @PostMapping("/responder")
    @ResponseStatus(HttpStatus.OK)
    public String enviarMensaje(@RequestParam String numero, @RequestParam String mensaje) throws NumberParseException {
        whatsAppService.sendTextMessage(numero,mensaje);
        return "Mensaje enviado con exito!";
    }

    @GetMapping("/getById/{id}")
    public MessageResponse getById(@PathVariable Long id) {
        return messageService.getById(id);
    }
}
