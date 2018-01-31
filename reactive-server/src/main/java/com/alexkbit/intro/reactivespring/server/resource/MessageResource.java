package com.alexkbit.intro.reactivespring.server.resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexkbit.intro.reactivespring.common.dto.MessageDto;
import com.alexkbit.intro.reactivespring.server.mapper.MessageMapper;
import com.alexkbit.intro.reactivespring.server.model.Message;
import com.alexkbit.intro.reactivespring.server.service.MessageService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("message")
public class MessageResource {

    private MessageMapper mapper;
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.POST)
    public Mono<MessageDto> save(@RequestBody Message message) {
        return messageService.save(message)
                .map(mapper::toDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<MessageDto> get(@RequestParam("id") String id) {
        return messageService.getById(id)
                .map(mapper::toDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Flux<MessageDto> getAll() {
        return messageService.findAll()
                .map(mapper::toDto);
    }

}
