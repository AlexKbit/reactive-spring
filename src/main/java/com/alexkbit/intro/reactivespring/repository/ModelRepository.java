package com.alexkbit.intro.reactivespring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.alexkbit.intro.reactivespring.model.Model;

public interface ModelRepository extends ReactiveMongoRepository<Model, String> {
}
