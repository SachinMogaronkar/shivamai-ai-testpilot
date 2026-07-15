package com.shivamai.systems.aitestpilot.chat.memory;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MemoryStore {

    private final Map<String, List<ChatMemory>> memories = new ConcurrentHashMap<>();

    public List<ChatMemory> findBySessionId(String sessionId) {
        return memories.computeIfAbsent(sessionId, id -> new CopyOnWriteArrayList<>());
    }

    public void save(String sessionId, ChatMemory memory) {
        findBySessionId(sessionId).add(memory);
    }

    public void clear(String sessionId) {
        memories.remove(sessionId);
    }

}