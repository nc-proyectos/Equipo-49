package com.nc.g49_smartcrm.controller;


import com.nc.g49_smartcrm.dto.TaskResponse;
import com.nc.g49_smartcrm.model.Task;
import com.nc.g49_smartcrm.service.NotificationService;
import com.nc.g49_smartcrm.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/internal/task")
@AllArgsConstructor
public class InternalTaskController {

    private final TaskService taskService;
    private final NotificationService notificationService;

    @PostMapping("/notify/{id}")
    public ResponseEntity<String> trigger(@PathVariable Long id) {

        TaskResponse taskResponse = taskService.findById(id);

        if (taskResponse.getStatus() == Task.Status.COMPLETED) {
            return ResponseEntity.ok("Task already completed");
        }

        String message = String.format("Reminder! Call to %s now.", taskResponse.getContactName());

        notificationService.sendReminder(taskResponse.getUserId(), taskResponse.getId(), message);

        return ResponseEntity.ok("notified");
    }
}
