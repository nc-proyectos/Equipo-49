package com.nc.g49_smartcrm.config;


import com.nc.g49_smartcrm.model.*;
import com.nc.g49_smartcrm.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final ConversationRepository conversationRepository;
    private final TaskRepository taskRepository;
    private final MessageRepository messageRepository;

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) {

        logger.info("DataLoader started");

        logger.info("Creating user");
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

        logger.info("Creating contact");
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

        logger.info("Creating conversation");
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

        logger.info("Creating Messages");
        if (messageRepository.findAll().isEmpty()) {

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
        }

        conversation.setLastMessageAt(Instant.now().plusSeconds(10));
        conversation.setChannel(Channel.WHATSAPP);
        conversationRepository.save(conversation);

        if (taskRepository.findAll().isEmpty()) {

            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 10; i++) {

                long secondsPast = ThreadLocalRandom.current().nextLong(0, 60 * 60 * 24 * 7L); // hasta 7 días atrás
                long secondsFuture = ThreadLocalRandom.current().nextLong(0, 60 * 60 * 24 * 5L); // hasta 5 días adelante

                Instant createdAt = Instant.now().minusSeconds(secondsPast);
                Instant reminderAt = Instant.now().plusSeconds(secondsFuture);

                Task task = Task.builder()
                        .contactName("Contacto " + i)
                        .message("Llamar a Contacto " + i)
                        .userId(user.getId())
                        .createdAt(createdAt)
                        .reminderAt(reminderAt)
                        .status(Task.Status.PENDING)
                        .build();

                tasks.add(task);
            }
            taskRepository.saveAll(tasks);
        }

        logger.info("Data load finished");
    }
}
