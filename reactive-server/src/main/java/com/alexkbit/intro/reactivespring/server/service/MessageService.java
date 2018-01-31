package com.alexkbit.intro.reactivespring.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexkbit.intro.reactivespring.server.model.Message;
import com.alexkbit.intro.reactivespring.server.repository.MessageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    public Mono<Message> save(Message message) {
        return repository.save(message);
    }

    public Mono<Message> getById(String id) {
        return repository.findById(id);
    }

    public Flux<Message> findAll() {
        return repository.findAll();
    }
}
