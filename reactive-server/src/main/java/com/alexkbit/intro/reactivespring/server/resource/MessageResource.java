package com.alexkbit.intro.reactivespring.server.resource;

import com.alexkbit.intro.reactivespring.common.dto.MessageDto;
import com.alexkbit.intro.reactivespring.server.mapper.MessageMapper;
import com.alexkbit.intro.reactivespring.server.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("message")
public class MessageResource {

    private MessageMapper mapper;
    private MessageService messageService;

    @PostMapping
    public Mono<MessageDto> save(@RequestBody MessageDto message) {
        log.debug("Save message = {}", message);
        return messageService
                    .save(mapper.toModel(message))
                    .map(mapper::toDto);
    }

    @GetMapping(value = "/{id}")
    public Mono<MessageDto> get(@PathVariable("id") String id) {
        log.debug("Get message by id = {}", id);
        return messageService.getById(id)
                .map(mapper::toDto);
    }

    @GetMapping
    public Flux<MessageDto> getAll() {
        log.debug("Get all messages");
        return messageService.findAll()
                .map(mapper::toDto)
                .publishOn(Schedulers.elastic());
    }

}
