package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.ConversationResponse;
import com.nc.g49_smartcrm.dto.ConversationStartRequest;
import com.nc.g49_smartcrm.dto.MessageRequest;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.service.ConversationService;
import com.nc.g49_smartcrm.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
@AllArgsConstructor
@Tag(name = "Conversations", description = "End points para conversaciones")
public class ConversationController {

    private final ConversationService conversationService;
    private final MessageService messageService;

    @Operation(summary = "Obtener todas las conversaciones")
    @GetMapping("/getAll")
    public List<ConversationResponse> getAll() {
        return conversationService.getAllConversations();
    }

    @Operation(summary = "Obtener una conversacion")
    @GetMapping("/getById/{id}")
    public ConversationResponse getById(@PathVariable int id) {
        return conversationService.getConversationById(id);
    }

    @Operation(summary = "Empezar una conversacion")
    @PostMapping("/start")
    public ConversationResponse startConversation(@RequestBody ConversationStartRequest request) {
        return conversationService.startConversation(request);
    }

    @Operation(summary = "Cerrar una conversacion")
    @PostMapping("/close/{id}")
    public ConversationResponse closeConversation(@PathVariable Long id) {
        return conversationService.closeConversation(id);
    }

    @Operation(summary = "Agregar mensaje a una conversacion")
    @PostMapping("/message/add/{conversationId}")
    public MessageResponse addMessage(
            @PathVariable Long conversationId,
            @RequestBody MessageRequest request
    ) {
        return messageService.addMessage(conversationId, request);
    }

    @Operation(summary = "Obtener todos los mensajes de una conversacion")
    @GetMapping("/message/getAll/{conversationId}")
    public List<MessageResponse> getMessages(@PathVariable Long conversationId) {
        return messageService.getMessages(conversationId);
    }

}
