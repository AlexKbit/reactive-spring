package com.alexkbit.intro.reactivespring.server.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexkbit.intro.reactivespring.common.dto.MessageDto;
import com.alexkbit.intro.reactivespring.server.mapper.MessageMapper;
import com.alexkbit.intro.reactivespring.server.model.Message;
import com.alexkbit.intro.reactivespring.server.service.MessageService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("message")
public class MessageResource {

    private MessageMapper mapper;
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.POST)
    public Mono<MessageDto> save(@RequestBody Message message) {
        log.debug("Save message = {}", message);
        return messageService.save(message)
                .map(mapper::toDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<MessageDto> get(@PathVariable("id") String id) {
        log.debug("Get message by id = {}", id);
        return messageService.getById(id)
                .map(mapper::toDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Flux<MessageDto> getAll() {
        log.debug("Get all messages");
        return messageService.findAll()
                .map(mapper::toDto);
    }

}
