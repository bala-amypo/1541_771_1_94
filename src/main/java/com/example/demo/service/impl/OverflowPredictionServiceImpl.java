package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository,
            ZoneRepository zoneRepository
    ) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        FillLevelRecord latestRecord = recordRepository
                .findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        UsagePatternModel model = modelRepository
                .findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No model found"));

        double remainingPercent = 100 - latestRecord.getFillPercentage();
        double dailyRate = model.getAvgDailyIncreaseWeekday();   // simplified

        int days = (dailyRate <= 0) ? 0 : (int) Math.ceil(remainingPercent / dailyRate);

        if (days < 0) throw new BadRequestException("Invalid prediction");

        LocalDate predictedDate = LocalDate.now().plus(days, ChronoUnit.DAYS);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(days);
        prediction.setPredictedFullDate(predictedDate);
        prediction.setGeneratedAt(new Timestamp(System.currentTimeMillis()));

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return predictionRepository.findAll()
                .stream()
                .filter(p -> p.getBin().equals(bin))
                .toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
