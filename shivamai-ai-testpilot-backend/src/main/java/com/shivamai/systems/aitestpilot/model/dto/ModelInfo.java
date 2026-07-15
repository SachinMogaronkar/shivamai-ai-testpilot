package com.shivamai.systems.aitestpilot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelInfo {

    // Ollama model name. Example: qwen2.5:1.5b
    private String name;

    // Whether this model is currently active.
    private boolean active;

}