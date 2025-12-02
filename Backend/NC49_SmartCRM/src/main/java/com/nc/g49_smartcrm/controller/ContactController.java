package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.ContactRequest;
import com.nc.g49_smartcrm.dto.ContactResponse;
import com.nc.g49_smartcrm.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contact")
@Tag(name = "Contacts", description = "End points para contactos")
public class ContactController {

    private final ContactService contactService;

    @Operation(summary = "Obtener todos los contactos")
    @GetMapping("/getAll")
    public ResponseEntity<List<ContactResponse>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @Operation(summary = "Obtener contacto por id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ContactResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getById(id));
    }

    @Operation(summary = "Guardar nuevo contacto")
    @PostMapping("/save")
    public ResponseEntity<ContactResponse> save(@RequestBody ContactRequest contactRequest) {
        return ResponseEntity.ok(contactService.createContact(contactRequest));
    }

    @Operation(summary = "Actualizar un contacto")
    @PutMapping("/update/{id}")
    public ResponseEntity<ContactResponse> update(@PathVariable Long id, @RequestBody ContactRequest contactRequest) {
        return ResponseEntity.ok(contactService.update(id, contactRequest));
    }

    @Operation(summary = "Borrar un contacto por id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
