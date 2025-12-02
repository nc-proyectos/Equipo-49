package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.TaskRequest;
import com.nc.g49_smartcrm.dto.TaskResponse;
import com.nc.g49_smartcrm.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
@Tag(name = "Tasks", description = "End points para tareas")
public class TaskController {

    private final TaskService service;
    private final TaskService taskService;

    @Operation(summary = "Guardar una tarea")
    @PostMapping("/save")
    public ResponseEntity<TaskResponse> save(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(service.createTask(taskRequest));
    }

    @Operation(summary = "Obtener una tarea")
    @GetMapping("/getById/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @Operation(summary = "Obtener tareas pendientes")
    @GetMapping("/getPending")
    public ResponseEntity<List<TaskResponse>> getPending(@RequestParam Long userId) {
        return ResponseEntity.ok(taskService.listPending(userId));
    }

    @Operation(summary = "Marcar como completada a una tarea")
    @PutMapping("/complete/{id}")
    public ResponseEntity<TaskResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(service.completeTask(id));
    }
}
