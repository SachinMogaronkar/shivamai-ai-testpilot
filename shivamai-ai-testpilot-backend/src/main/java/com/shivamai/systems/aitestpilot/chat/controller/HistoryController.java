package com.shivamai.systems.aitestpilot.chat.controller;

import com.shivamai.systems.aitestpilot.chat.history.ChatHistory;
import com.shivamai.systems.aitestpilot.chat.history.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final ChatHistoryService chatHistoryService;

    @GetMapping("/{sessionId}")
    public ChatHistory getHistory(
            @PathVariable String sessionId
    ) {
        return chatHistoryService.getHistory(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public void clearHistory(
            @PathVariable String sessionId
    ) {
        chatHistoryService.clearHistory(sessionId);
    }

}