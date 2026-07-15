package com.shivamai.systems.aitestpilot.chat.session;

import com.shivamai.systems.aitestpilot.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private final SessionStore sessionStore;

    // Creates a new chat session.
    public ChatSession createSession() {

        ChatSession session = ChatSession.builder()
                .sessionId(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .lastAccessedAt(LocalDateTime.now())
                .build();

        sessionStore.save(session);

        return session;
    }

    // Returns an existing session.
    public ChatSession getSession(String sessionId) {

        ChatSession session = sessionStore.findById(sessionId);

        if (session == null) {
            return null;
        }

        session.setLastAccessedAt(LocalDateTime.now());

        sessionStore.save(session);

        return session;
    }

    // Returns an existing session if found, otherwise creates a new one.
    public ChatSession getOrCreateSession(String sessionId) {

        if (sessionId == null || sessionId.isBlank()) {
            return createSession();
        }

        ChatSession session = getSession(sessionId);

        if (session == null) {
            throw new ResourceNotFoundException(
                    "Session not found: " + sessionId
            );
        }

        return session;
    }

    public Collection<ChatSession> getAllSessions() {
        return sessionStore.findAll();
    }

    public void clearSessions() {
        sessionStore.clear();
    }

    // Deletes a session.
    public void deleteSession(String sessionId) {
        sessionStore.delete(sessionId);
    }

}