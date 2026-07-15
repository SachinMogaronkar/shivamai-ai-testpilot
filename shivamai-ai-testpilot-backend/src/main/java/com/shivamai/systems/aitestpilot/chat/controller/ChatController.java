package com.shivamai.systems.aitestpilot.chat.controller;

import com.shivamai.systems.aitestpilot.chat.dto.ChatRequest;
import com.shivamai.systems.aitestpilot.chat.dto.ChatResponse;
import com.shivamai.systems.aitestpilot.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatResponse chat(
            @Valid
            @RequestBody ChatRequest request
    ) {
        return chatService.chat(request);
    }

    @PostMapping(
            value = "/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> stream(
            @Valid
            @RequestBody ChatRequest request
    ) {
        return chatService.stream(request);
    }

}