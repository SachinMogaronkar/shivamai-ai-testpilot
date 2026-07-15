package com.shivamai.systems.aitestpilot.chat.history;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HistoryStore {

    private final Map<String, ChatHistory> histories = new ConcurrentHashMap<>();

    public void save(ChatHistory history) {
        histories.put(history.getSessionId(), history);
    }

    public ChatHistory findBySessionId(String sessionId) {
        return histories.get(sessionId);
    }

    public boolean exists(String sessionId) {
        return histories.containsKey(sessionId);
    }

    public void delete(String sessionId) {
        histories.remove(sessionId);
    }

}