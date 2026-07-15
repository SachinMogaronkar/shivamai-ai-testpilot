package com.shivamai.systems.aitestpilot.model.controller;

import com.shivamai.systems.aitestpilot.model.dto.ModelInfo;
import com.shivamai.systems.aitestpilot.model.dto.ModelSelectionRequest;
import com.shivamai.systems.aitestpilot.model.dto.ModelSelectionResponse;
import com.shivamai.systems.aitestpilot.model.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    // Returns all available models.
    @GetMapping
    public List<ModelInfo> getAvailableModels() {
        return modelService.getAvailableModels();
    }

    // Returns the currently selected model.
    @GetMapping("/current")
    public ModelSelectionResponse getCurrentModel() {

        return new ModelSelectionResponse(
                modelService.getCurrentModel()
        );
    }

    // Changes the active model.
    @PostMapping("/select")
    public ModelSelectionResponse selectModel(
            @Valid
            @RequestBody ModelSelectionRequest request
    ) {

        modelService.selectModel(request.model());

        return new ModelSelectionResponse(
                modelService.getCurrentModel()
        );
    }

}