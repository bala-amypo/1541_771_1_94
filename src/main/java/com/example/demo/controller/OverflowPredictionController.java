package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/generate/{binId}")
    public ResponseEntity<ApiResponse> generate(@PathVariable Long binId) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Prediction generated", predictionService.generatePrediction(binId))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OverflowPrediction> get(@PathVariable Long id) {
        return ResponseEntity.ok(predictionService.getPredictionById(id));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<OverflowPrediction>> getForBin(@PathVariable Long binId) {
        return ResponseEntity.ok(predictionService.getPredictionsForBin(binId));
    }

    @GetMapping("/zone/{zoneId}/latest")
    public ResponseEntity<List<OverflowPrediction>> latestForZone(@PathVariable Long zoneId) {
        return ResponseEntity.ok(predictionService.getLatestPredictionsForZone(zoneId));
    }
}
