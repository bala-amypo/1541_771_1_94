package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/predictions")
@Tag(name = "Overflow Prediction API", description = "Overflow forecasting")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @PostMapping("/generate/{binId}")
    public OverflowPrediction generate(@PathVariable Long binId) {
        return service.generatePrediction(binId);
    }

    @GetMapping("/{id}")
    public OverflowPrediction get(@PathVariable Long id) {
        return service.getPredictionById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<OverflowPrediction> getByBin(@PathVariable Long binId) {
        return service.getPredictionsForBin(binId);
    }

    @GetMapping("/zone/{zoneId}/latest")
    public List<OverflowPrediction> getLatestByZone(@PathVariable Long zoneId) {
        return service.getLatestPredictionsForZone(zoneId);
    }
}
