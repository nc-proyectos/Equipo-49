package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ContactRequest;
import com.nc.g49_smartcrm.dto.ContactResponse;
import com.nc.g49_smartcrm.exception.ContactNotFoundException;
import com.nc.g49_smartcrm.mapper.ContactMapper;
import com.nc.g49_smartcrm.model.Contact;
import com.nc.g49_smartcrm.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public List<ContactResponse> getAll() {
        return contactRepository
                .findAllByOrderByLastnameAsc()
                .stream()
                .map(contactMapper::toDto)
                .toList();
    }

    @Override
    public ContactResponse getById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));

        return contactMapper.toDto(contact);
    }

    @Override
    public ContactResponse createContact(ContactRequest contactRequest) {
        Contact saved = contactRepository.save(contactMapper.toEntity(contactRequest));

        //TODO set ownerId & ownerName
        //TODO Validar existencia para no crear duplicados

        return contactMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ContactResponse update(Long id, ContactRequest contactRequest) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));

        contactMapper.updateEntityFromRequest(contactRequest, contact);
        return contactMapper.toDto(contact);
    }

    @Override
    public void delete(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        //TODO delete conversations and messages

        contactRepository.deleteById(id);
    }
}