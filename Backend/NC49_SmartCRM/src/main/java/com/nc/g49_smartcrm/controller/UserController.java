package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.UserRequest;
import com.nc.g49_smartcrm.dto.UserResponse;
import com.nc.g49_smartcrm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "Users", description = "End points para usuarios")
public class UserController {

    private UserService userService;

    @Operation(summary = "Obtener a todos los usuarios")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Obtener a un usuario")
    @GetMapping("/getById/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Guardar a un usuario")
    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @Operation(summary = "Actualizar a un usuario")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @Operation(summary = "Borrar a un usuario")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
