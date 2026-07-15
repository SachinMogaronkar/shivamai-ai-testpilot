package com.shivamai.systems.aitestpilot.chat.session;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionStore {

    private final Map<String, ChatSession> sessions = new ConcurrentHashMap<>();

    //Store a new session.
    public void save(ChatSession session) {
        sessions.put(session.getSessionId(), session);
    }

    //Retrieve a session by its ID.
    public ChatSession findById(String sessionId) {
        return sessions.get(sessionId);
    }

    //Check if a session exists.
    public boolean exists(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    //Delete a session.
    public void delete(String sessionId) {
        sessions.remove(sessionId);
    }

    //Return all active sessions.
    public Collection<ChatSession> findAll() {
        return sessions.values();
    }

    //Number of active sessions.
    public int count() {
        return sessions.size();
    }

    //Remove every session.
    public void clear() {
        sessions.clear();
    }
}