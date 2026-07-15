package com.shivamai.systems.aitestpilot.chat.memory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemory {

    // Session associated with this memory.
    private String sessionId;

    // Key of the remembered information. Example: userName
    private String key;

    // Stored value. Example: Sachin
    private String value;

    // Creation time.
    private LocalDateTime createdAt;

}