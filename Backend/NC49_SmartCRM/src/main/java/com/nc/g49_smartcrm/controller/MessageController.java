package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.MessageResponse;
import com.nc.g49_smartcrm.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @GetMapping("/getById/{id}")
    public MessageResponse getById(@PathVariable Long id) {
        return messageService.getById(id);
    }
}
