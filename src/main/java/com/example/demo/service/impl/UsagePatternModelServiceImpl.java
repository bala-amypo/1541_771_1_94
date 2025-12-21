package com.example.demo.service.impl;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.service.UsagePatternModelService;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository repository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() < 0 ||
            model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Daily increase must be non-negative");
        }
        return repository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        existing.setAvgDailyIncreaseWeekday(model.getAvgDailyIncreaseWeekday());
        existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend());
        return repository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        return repository.findLatestByBinId(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found for bin"));
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return repository.findAll();
    }
}

