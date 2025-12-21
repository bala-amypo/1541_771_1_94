package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.OverflowPredictionService;

@Service
 public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepo;
    private final UsagePatternModelRepository modelRepo;
    private final BinRepository binRepo;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepo,
                                         UsagePatternModelRepository modelRepo,
                                         BinRepository binRepo) {
        this.predictionRepo = predictionRepo;
        this.modelRepo = modelRepo;
        this.binRepo = binRepo;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {

        Bin bin = binRepo.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        UsagePatternModel model = modelRepo.findLatestByBinId(binId)
                .orElseThrow(() -> new ResourceNotFoundException("No usage model"));

        int days = (int) Math.ceil(bin.getCapacityLiters() /
                model.getAvgDailyIncreaseWeekday());

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setDaysUntilFull(days);
        prediction.setPredictedFullDate(java.sql.Date.valueOf(LocalDate.now().plusDays(days)));
        prediction.setModelUsed(model);

        return predictionRepo.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        return predictionRepo.findByBinId(binId);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        return predictionRepo.findLatestByZoneId(zoneId);
    }
}
