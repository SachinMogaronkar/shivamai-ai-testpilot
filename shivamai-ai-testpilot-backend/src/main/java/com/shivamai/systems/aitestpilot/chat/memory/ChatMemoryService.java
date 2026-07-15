package com.shivamai.systems.aitestpilot.chat.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMemoryService {

    private final MemoryStore memoryStore;

    /**
     * Stores a memory for a session.
     */
    public void remember(
            String sessionId,
            String key,
            String value
    ) {

        ChatMemory memory = ChatMemory.builder()
                .sessionId(sessionId)
                .key(key)
                .value(value)
                .createdAt(LocalDateTime.now())
                .build();

        memoryStore.save(sessionId, memory);
    }

    /**
     * Returns all memories for a session.
     */
    public List<ChatMemory> recall(String sessionId) {
        return memoryStore.findBySessionId(sessionId);
    }

    /**
     * Clears all memories for a session.
     */
    public void forget(String sessionId) {
        memoryStore.clear(sessionId);
    }

}