package com.alexkbit.intro.reactivespring.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String id;
    private String senderId;
    private String receiverId;
    private String message;
}
