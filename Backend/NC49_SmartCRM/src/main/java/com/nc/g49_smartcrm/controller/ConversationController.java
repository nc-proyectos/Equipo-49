package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.ConversationResponse;
import com.nc.g49_smartcrm.dto.ConversationStartRequest;
import com.nc.g49_smartcrm.dto.MessageRequest;
import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.service.ConversationService;
import com.nc.g49_smartcrm.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
@AllArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    private final MessageService messageService;

    @GetMapping("/getAll")
    public List<ConversationResponse> getAll() {
        return conversationService.getAllConversations();
    }

    @GetMapping("/getById/{id}")
    public ConversationResponse getById(@PathVariable int id) {
        return conversationService.getConversationById(id);
    }

    @PostMapping("/start")
    public ConversationResponse startConversation(@RequestBody ConversationStartRequest request) {
        return conversationService.startConversation(request);
    }

    @PostMapping("/close/{id}")
    public ConversationResponse closeConversation(@PathVariable Long id) {
        return conversationService.closeConversation(id);
    }

    @PostMapping("/message/add/{conversationId}")
    public MessageResponse addMessage(
            @PathVariable Long conversationId,
            @RequestBody MessageRequest request
    ) {
        return messageService.addMessage(conversationId, request);
    }

    @GetMapping("/message/getAll/{conversationId}")
    public List<MessageResponse> getMessages(@PathVariable Long conversationId) {
        return messageService.getMessages(conversationId);
    }


}
