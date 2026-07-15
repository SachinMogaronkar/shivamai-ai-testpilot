package com.shivamai.systems.aitestpilot.chat.service;

import com.shivamai.systems.aitestpilot.chat.dto.ChatRequest;
import com.shivamai.systems.aitestpilot.chat.dto.ChatResponse;
import com.shivamai.systems.aitestpilot.chat.history.ChatHistoryService;
import com.shivamai.systems.aitestpilot.chat.memory.ChatMemoryService;
import com.shivamai.systems.aitestpilot.chat.session.ChatSession;
import com.shivamai.systems.aitestpilot.chat.session.ChatSessionService;
import com.shivamai.systems.aitestpilot.model.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    private final ChatSessionService chatSessionService;

    private final ChatHistoryService chatHistoryService;

    private final ChatMemoryService chatMemoryService;

    private final ModelService modelService;

    public ChatResponse chat(ChatRequest request) {

        long startTime = System.currentTimeMillis();

        log.info("Chat request received. SessionId={}", request.sessionId());

        // Create or load session.
        ChatSession session =
                chatSessionService.getOrCreateSession(request.sessionId());

        log.info("Using Session={}", session.getSessionId());

        // Store user message.
        chatHistoryService.addUserMessage(
                session.getSessionId(),
                request.message()
        );

        log.info("Using Model={}", modelService.getCurrentModel());

        // Call LLM.
        String conversation =
                chatHistoryService.buildPrompt(session.getSessionId());

        String prompt = """
            You are a helpful AI assistant.
            
            Below is the conversation so far.
            
            %s
            
            Reply to the latest user message.
            """.formatted(conversation);

        log.debug("Prompt={}", prompt);

        String aiResponse =
                chatClient.prompt()
                        .options(
                                OllamaChatOptions.builder()
                                        .model(modelService.getCurrentModel())
                        )
                        .user(prompt)
                        .call()
                        .content();

        log.info("AI response generated.");

        // Store assistant message.
        chatHistoryService.addAssistantMessage(
                session.getSessionId(),
                aiResponse
        );

        long endTime = System.currentTimeMillis();

        log.info(
                "Request completed in {} ms",
                endTime - startTime
        );

        // Return response.
        return new ChatResponse(
                session.getSessionId(),
                aiResponse
        );
    }

    public Flux<String> stream(ChatRequest request) {

        ChatSession session =
                chatSessionService.getOrCreateSession(request.sessionId());

        chatHistoryService.addUserMessage(
                session.getSessionId(),
                request.message()
        );

        String conversation =
                chatHistoryService.buildPrompt(session.getSessionId());

        String prompt = """
            You are a helpful AI assistant.

            Below is the conversation so far.

            %s

            Reply to the latest user message.
            """.formatted(conversation);

        StringBuilder response = new StringBuilder();

        return chatClient.prompt()
                .options(
                        OllamaChatOptions.builder()
                                .model(modelService.getCurrentModel())
                )
                .user(prompt)
                .stream()
                .content()
                .doOnNext(response::append)
                .doOnComplete(() ->
                        chatHistoryService.addAssistantMessage(
                                session.getSessionId(),
                                response.toString()
                        )
                );
    }

}