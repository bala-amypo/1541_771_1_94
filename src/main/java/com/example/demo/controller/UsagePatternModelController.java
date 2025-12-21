package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/models")
@Tag(name = "Usage Pattern Model API", description = "Usage pattern analytics")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping
    public UsagePatternModel create(@RequestBody UsagePatternModel model) {
        return service.createModel(model);
    }

    @PutMapping("/{id}")
    public UsagePatternModel update(@PathVariable Long id,
                                    @RequestBody UsagePatternModel model) {
        return service.updateModel(id, model);
    }

    @GetMapping("/bin/{binId}")
    public UsagePatternModel getByBin(@PathVariable Long binId) {
        return service.getModelForBin(binId);
    }

    @GetMapping
    public List<UsagePatternModel> getAll() {
        return service.getAllModels();
    }
}
