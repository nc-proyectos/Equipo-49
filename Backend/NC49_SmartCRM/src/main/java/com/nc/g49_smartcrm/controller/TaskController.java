package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.TaskRequest;
import com.nc.g49_smartcrm.dto.TaskResponse;
import com.nc.g49_smartcrm.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService service;
    private final TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<TaskResponse> save(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(service.createTask(taskRequest));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping("/getPending")
    public ResponseEntity<List<TaskResponse>> getPending(@RequestParam Long userId) {
        return ResponseEntity.ok(taskService.listPending(userId));
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<TaskResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(service.completeTask(id));
    }
}
