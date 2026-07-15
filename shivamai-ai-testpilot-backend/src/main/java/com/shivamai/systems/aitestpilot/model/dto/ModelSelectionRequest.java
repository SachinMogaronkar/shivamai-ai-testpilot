package com.shivamai.systems.aitestpilot.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ModelSelectionRequest(

        @NotBlank(message = "Model name cannot be blank")
        String model

) {
}