package com.shivamai.systems.aitestpilot.chat.controller;

import com.shivamai.systems.aitestpilot.chat.session.ChatSession;
import com.shivamai.systems.aitestpilot.chat.session.ChatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final ChatSessionService chatSessionService;

    @GetMapping
    public Collection<ChatSession> getAllSessions() {
        return chatSessionService.getAllSessions();
    }

    @GetMapping("/{sessionId}")
    public ChatSession getSession(
            @PathVariable String sessionId
    ) {
        return chatSessionService.getSession(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public void deleteSession(
            @PathVariable String sessionId
    ) {
        chatSessionService.deleteSession(sessionId);
    }

    @DeleteMapping
    public void clearSessions() {
        chatSessionService.clearSessions();
    }

}