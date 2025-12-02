package com.nc.g49_smartcrm.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.dto.WhatsAppWebhook;
import com.nc.g49_smartcrm.service.MessageService;
import com.nc.g49_smartcrm.service.WhatsAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@Tag(name = "Messages", description = "End points para mensajes")
public class MessageController {

    private final WhatsAppService whatsAppService;
    private final MessageService messageService;

    //la api de wpp verifica la url a la q le va a mandar los enventos.(en este caso mensajes)
    @GetMapping("/whatsapp")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Solo para uso de la api de whatsapp, verifica si la url es valida.")
    public String verifyUrl(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.challenge") String challenge,
            @RequestParam(name = "hub.verify_token") String token) {

        return whatsAppService.verifyUrl(mode, challenge, token);
    }

    //el end point q recibe los mensajes desde la api de wpp.
    @Operation(summary = "Solo para uso de la api de whatsapp, recibe los mensajes.")
    @PostMapping("/whatsapp")
    @ResponseStatus(HttpStatus.OK)
    public void receiveMessage(@RequestBody WhatsAppWebhook webhook) {
        whatsAppService.receiveMessage(webhook);
    }

    @Operation(summary = "Enviar un mensaje a un cliente.")
    @PostMapping("/responder")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearer-key")
    public String enviarMensaje(@RequestParam String numero, @RequestParam String mensaje) throws NumberParseException {
        whatsAppService.sendTextMessage(numero, mensaje);
        return "Mensaje enviado con exito!";
    }

    @Operation(summary = "Obtener mensaje por id.")
    @GetMapping("/getById/{id}")
    public MessageResponse getById(@PathVariable Long id) {
        return messageService.getById(id);
    }
}
