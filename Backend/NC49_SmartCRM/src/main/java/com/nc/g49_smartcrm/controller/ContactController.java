package com.nc.g49_smartcrm.controller;

import com.nc.g49_smartcrm.dto.ContactRequest;
import com.nc.g49_smartcrm.dto.ContactResponse;
import com.nc.g49_smartcrm.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ContactResponse>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ContactResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ContactResponse> save(@RequestBody ContactRequest contactRequest) {
        return ResponseEntity.ok(contactService.createContact(contactRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContactResponse> update(@PathVariable Long id, @RequestBody ContactRequest contactRequest) {
        return ResponseEntity.ok(contactService.update(id, contactRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
