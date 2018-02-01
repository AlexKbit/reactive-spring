package com.alexkbit.intro.reactivespring.server.mapper;

import org.springframework.stereotype.Component;

import com.alexkbit.intro.reactivespring.common.dto.MessageDto;
import com.alexkbit.intro.reactivespring.server.model.Message;

/**
 * Mapper for {@link Message} and {@link MessageDto}.
 */
@Component
public class MessageMapper {

    public MessageDto toDto(Message msg) {
        return new MessageDto(msg.getId(), msg.getSenderId(), msg.getReceiverId(), msg.getMessage());
    }

    public Message toModel(MessageDto dto) {
        return new Message(dto.getId(), dto.getSenderId(), dto.getReceiverId(), dto.getMessage());
    }
}
