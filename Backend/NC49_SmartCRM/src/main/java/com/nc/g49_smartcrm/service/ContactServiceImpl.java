package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.ContactRequest;
import com.nc.g49_smartcrm.dto.ContactResponse;
import com.nc.g49_smartcrm.exception.ContactNotFoundException;
import com.nc.g49_smartcrm.exception.EmailAlreadyExistException;
import com.nc.g49_smartcrm.mapper.ContactMapper;
import com.nc.g49_smartcrm.model.Contact;
import com.nc.g49_smartcrm.model.ContactStatus;
import com.nc.g49_smartcrm.model.Conversation;
import com.nc.g49_smartcrm.model.User;
import com.nc.g49_smartcrm.repository.ContactRepository;
import com.nc.g49_smartcrm.repository.ConversationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private UserService userService;
    private ConversationRepository conversationRepository;

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

        List<Conversation> conversations = conversationRepository.findAllByContact_Id(id);
        conversationRepository.deleteAll(conversations);
        contactRepository.deleteById(id);
    }

    @Override
    public ContactResponse findByPhoneOrCreateNewContact(String phone, ContactRequest request) {

        User user = userService.getCurrentUser();
        Contact contact = contactRepository.findByPhone(phone)
                .orElseGet(() -> contactRepository.save(Contact.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .email(request.getEmail())
                        .phone(request.getPhone())
                        .status(request.getStatus())
                        .source(request.getSource())
                        .owner(user)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build()
                ));

        return contactMapper.toDto(contact);
    }

    @Override
    public ContactResponse createContact(ContactRequest contactRequest) {
        if (contactRepository.existsByEmail(contactRequest.getEmail())) {
            throw new EmailAlreadyExistException("Email " + contactRequest.getEmail() + " already exist");
        }
        Contact contact = contactMapper.toEntity(contactRequest);
        User user = userService.getCurrentUser();
        contact.setOwner(user);
        contact.setStatus(ContactStatus.LEAD_ACTIVE);
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.toDto(savedContact);
    }
}