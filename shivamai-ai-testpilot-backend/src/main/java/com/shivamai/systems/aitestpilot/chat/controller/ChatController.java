package com.shivamai.systems.aitestpilot.chat.controller;

import com.shivamai.systems.aitestpilot.chat.dto.ChatRequest;
import com.shivamai.systems.aitestpilot.chat.dto.ChatResponse;
import com.shivamai.systems.aitestpilot.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String response = chatService.chat(request.message());

        return new ChatResponse(response);
    }
}