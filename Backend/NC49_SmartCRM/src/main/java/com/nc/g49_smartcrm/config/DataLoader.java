package com.nc.g49_smartcrm.config;


import com.nc.g49_smartcrm.model.*;
import com.nc.g49_smartcrm.repository.ContactRepository;
import com.nc.g49_smartcrm.repository.ConversationRepository;
import com.nc.g49_smartcrm.repository.MessageRepository;
import com.nc.g49_smartcrm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {

        logger.info("DataLoader started");

        User user = userRepository.findAll().stream()
                .findFirst()
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .firstname("Admin")
                            .lastname("User")
                            .email("admin@mail.com")
                            .password("112233")
                            .role(Role.ADMIN)
                            .createdAt(Instant.now())
                            .updatedAt(Instant.now())
                            .build();
                    return userRepository.save(newUser);
                });

        Contact contact = contactRepository.findAll().stream()
                .findFirst().orElseGet(() -> {
                    Contact newcontact = Contact.builder()
                            .firstname("Client")
                            .lastname("Demo")
                            .email("client@mail.com")
                            .phone("+54666456456")
                            .status(ContactStatus.LEAD_ACTIVE)
                            .source(ContactSource.WHATSAPP)
                            .createdAt(Instant.now())
                            .updatedAt(Instant.now())
                            .build();
                    return contactRepository.save(newcontact);
                });

        Conversation conversation = conversationRepository.findAll().stream().findFirst().orElseGet(() -> {
            Conversation newConversation = Conversation.builder()
                    .subject("First contact")
                    .status(ConversationStatus.OPEN)
                    .contact(contact)
                    .user(user)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
            return conversationRepository.save(newConversation);
        });

        List<Message> messages = List.of(
                Message.builder()
                        .conversation(conversation)
                        .senderType(SenderType.AGENT)
                        .body("Hola!  soy el agente")
                        .createdAt(Instant.now())
                        .senderId(user.getId())
                        .direction(MessageDirection.OUTBOUND)
                        .messageStatus(MessageStatus.SENT)
                        .build(),
                Message.builder()
                        .conversation(conversation)
                        .senderType(SenderType.CONTACT)
                        .body("Hola soy el contacto!.")
                        .createdAt(Instant.now().plusSeconds(5))
                        .senderId(contact.getId())
                        .direction(MessageDirection.INBOUND)
                        .messageStatus(MessageStatus.SENT)
                        .build(),

                Message.builder()
                        .conversation(conversation)
                        .senderType(SenderType.AGENT)
                        .body("y yo soy el agente…")
                        .createdAt(Instant.now().plusSeconds(10))
                        .senderId(user.getId())
                        .direction(MessageDirection.OUTBOUND)
                        .messageStatus(MessageStatus.SENT)
                        .build()
        );

        messageRepository.saveAll(messages);

        conversation.setLastMessageAt(Instant.now().plusSeconds(10));
        conversation.setChannel(Channel.WHATSAPP);
        conversationRepository.save(conversation);

        logger.info("Data load finished");
    }
}
