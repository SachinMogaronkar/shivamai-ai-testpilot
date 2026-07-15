package com.shivamai.systems.aitestpilot.chat.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    // USER or ASSISTANT
    private MessageRole role;

    // Actual message content.

    private String content;

    // Message creation time.
    private LocalDateTime timestamp;

}