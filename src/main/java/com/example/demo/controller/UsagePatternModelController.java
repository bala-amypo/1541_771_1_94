package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Model created", modelService.createModel(model))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,
                                              @RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Model updated", modelService.updateModel(id, model))
        );
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getForBin(@PathVariable Long binId) {
        return ResponseEntity.ok(modelService.getModelForBin(binId));
    }

    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAll() {
        return ResponseEntity.ok(modelService.getAllModels());
    }
}
