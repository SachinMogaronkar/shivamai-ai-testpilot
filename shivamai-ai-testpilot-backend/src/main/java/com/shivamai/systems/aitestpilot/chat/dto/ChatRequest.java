package com.shivamai.systems.aitestpilot.chat.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(

        String sessionId,

        @NotBlank(message = "Message cannot be blank")
        String message

) {
}