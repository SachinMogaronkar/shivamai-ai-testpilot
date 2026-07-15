package com.shivamai.systems.aitestpilot.model.service;

import com.shivamai.systems.aitestpilot.model.dto.ModelInfo;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class ModelService {

    private String currentModel = "qwen2.5:1.5b";

    public List<ModelInfo> getAvailableModels() {

        return List.of(
                ModelInfo.builder()
                        .name("qwen2.5:1.5b")
                        .active(currentModel.equals("qwen2.5:1.5b"))
                        .build(),

                ModelInfo.builder()
                        .name("phi3:mini")
                        .active(currentModel.equals("phi3:mini"))
                        .build()
        );
    }

    public void selectModel(String model) {
        this.currentModel = model;
    }

}