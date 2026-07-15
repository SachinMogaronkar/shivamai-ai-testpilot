package com.shivamai.systems.aitestpilot.chat.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {

    // Session to which this history belongs.
    private String sessionId;

    // Ordered conversation.
    @Builder.Default
    private List<ChatMessage> messages = new ArrayList<>();

}