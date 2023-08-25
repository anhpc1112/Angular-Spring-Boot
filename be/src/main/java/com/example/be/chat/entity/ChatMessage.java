package com.example.be.chat.entity;

import com.example.be.enums.MessageType;
import lombok.*;
import org.springframework.messaging.handler.annotation.SendTo;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
}
