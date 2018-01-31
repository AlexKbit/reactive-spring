package com.alexkbit.intro.reactivespring.server.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.alexkbit.intro.reactivespring.server.model.Message;

/**
 * Spring Data reactive repository for {@link Message}.
 */
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
}
