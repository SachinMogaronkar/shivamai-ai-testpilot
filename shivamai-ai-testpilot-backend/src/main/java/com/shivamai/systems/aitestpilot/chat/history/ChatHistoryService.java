package com.shivamai.systems.aitestpilot.chat.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    private final HistoryStore historyStore;

    // Returns existing history or creates a new one.
    public ChatHistory getOrCreateHistory(String sessionId) {

        ChatHistory history = historyStore.findBySessionId(sessionId);

        if (history == null) {

            history = ChatHistory.builder()
                    .sessionId(sessionId)
                    .build();

            historyStore.save(history);
        }

        return history;
    }

    // Adds a user message.
    public void addUserMessage(String sessionId, String message) {
        addMessage(sessionId, MessageRole.USER, message);
    }

    // Adds an assistant message.
    public void addAssistantMessage(String sessionId, String message) {
        addMessage(sessionId, MessageRole.ASSISTANT, message);
    }

    // Returns conversation history.
    public ChatHistory getHistory(String sessionId) {
        return getOrCreateHistory(sessionId);
    }

    // Clears conversation history.
    public void clearHistory(String sessionId) {
        historyStore.delete(sessionId);
    }

    // Builds the prompt from the conversation history.
    public String buildPrompt(String sessionId) {

        ChatHistory history = getHistory(sessionId);

        StringBuilder prompt = new StringBuilder();

        for (ChatMessage message : history.getMessages()) {

            prompt.append(message.getRole().name())
                    .append(": ")
                    .append(message.getContent())
                    .append("\n");
        }

        return prompt.toString();
    }

    // Adds a message to the conversation history.

    private void addMessage(
            String sessionId,
            MessageRole role,
            String content
    ) {

        ChatHistory history = getOrCreateHistory(sessionId);

        history.getMessages().add(
                ChatMessage.builder()
                        .role(role)
                        .content(content)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        historyStore.save(history);
    }

}