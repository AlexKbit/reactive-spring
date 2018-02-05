package com.alexkbit.intro.reactivespring.server.resource;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.alexkbit.intro.reactivespring.common.dto.MessageDto;
import com.alexkbit.intro.reactivespring.server.model.Message;
import com.alexkbit.intro.reactivespring.server.repository.MessageRepository;

import reactor.core.publisher.Mono;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MessageResourceTest {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageResource resource;

    private WebTestClient client;
    private Message message;

    @PostConstruct
    public void init() {
        client = WebTestClient
                .bindToController(resource)
                .configureClient()
                .baseUrl("message")
                .build();

        message = repository.save(createMessage()).block();
    }

    @Test
    public void shouldSave() {
        Message msg = createMessage();
        client.post()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(msg), Message.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.senderId").isEqualTo(msg.getSenderId())
                .jsonPath("$.receiverId").isEqualTo(msg.getReceiverId())
                .jsonPath("$.message").isEqualTo(msg.getMessage());
    }

    @Test
    public void shouldGetAll() {
        client.get()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(MessageDto.class)
                .hasSize(1);
    }

    @Test
    public void shouldGetById() {
        client.get()
                .uri("/{id}", message.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isEqualTo(message.getId());
    }

    private Message createMessage() {
        return new Message(null,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "msg");
    }

}