package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ContactRequest;
import com.nc.g49_smartcrm.dto.ContactResponse;

import java.util.List;

public interface ContactService {
    List<ContactResponse> getAll();

    ContactResponse getById(Long id);

    ContactResponse createContact(ContactRequest contactRequest);

    ContactResponse update(Long id, ContactRequest contactRequest);

    void delete(Long id);
}
