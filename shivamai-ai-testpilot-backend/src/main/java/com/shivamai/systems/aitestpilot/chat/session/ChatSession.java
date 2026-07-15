package com.shivamai.systems.aitestpilot.chat.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatSession {

    //Unique identifier for a conversation session.
    private String sessionId;

    //Timestamp when the session was created.
    private LocalDateTime createdAt;

    //Timestamp of the most recent interaction.
    private LocalDateTime lastAccessedAt;

}