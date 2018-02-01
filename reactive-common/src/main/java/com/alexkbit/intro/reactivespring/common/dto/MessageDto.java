package com.alexkbit.intro.reactivespring.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MessageDto {
    private String id;
    private String senderId;
    private String receiverId;
    private String message;
}
