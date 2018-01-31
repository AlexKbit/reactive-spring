package com.alexkbit.intro.reactivespring.client;

import java.time.Duration;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.alexkbit.intro.reactivespring.common.dto.MessageDto;

import reactor.core.publisher.Flux;

/**
 * Reactive client fro {@link MessageDto}.
 */
public class MessageClient {

    private static final String DEFAULT_URL = "http://localhost:9000/message";
    private static final long DEFAULT_WAIT = 6L;
    private WebClient client = WebClient.create(DEFAULT_URL);

    public Flux<MessageDto> save(MessageDto message) {
        return client.post()
                .body(BodyInserters.fromObject(message))
                .exchange()
                .flatMapMany(cr -> cr.bodyToFlux(MessageDto.class))
                .onErrorResume(err -> Flux.empty())
                .take(Duration.ofSeconds(DEFAULT_WAIT));
    }

    public Flux<MessageDto> get(String id) {
        return client.get()
                .uri("/{id}", id)
                .exchange()
                .flatMapMany(cr -> cr.bodyToFlux(MessageDto.class))
                .onErrorResume(err -> Flux.empty())
                .take(Duration.ofSeconds(DEFAULT_WAIT));
    }

    public Flux<MessageDto> getAll() {
        return client.get()
                .exchange()
                .flatMapMany(cr -> cr.bodyToFlux(MessageDto.class))
                .onErrorResume(err -> Flux.empty())
                .take(Duration.ofSeconds(DEFAULT_WAIT));
    }
}
