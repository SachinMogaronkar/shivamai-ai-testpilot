package com.shivamai.systems.aitestpilot.chat.controller;

import com.shivamai.systems.aitestpilot.chat.memory.ChatMemory;
import com.shivamai.systems.aitestpilot.chat.memory.ChatMemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memory")
@RequiredArgsConstructor
public class MemoryController {

    private final ChatMemoryService chatMemoryService;

    @GetMapping("/{sessionId}")
    public List<ChatMemory> recall(
            @PathVariable String sessionId
    ) {
        return chatMemoryService.recall(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public void forget(
            @PathVariable String sessionId
    ) {
        chatMemoryService.forget(sessionId);
    }

}