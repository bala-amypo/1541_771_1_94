package com.example.demo.model;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Bin bin;

    @FutureOrPresent
    private Date predictedFullDate;

    @NotNull
    @Min(0)
    private Integer daysUntilFull;

    @ManyToOne
    private UsagePatternModel modelUsed;

    private Instant generatedAt;

    @PrePersist
    public void onCreate() {
        generatedAt = Instant.now();
    }

    public OverflowPrediction() {
    }

    public OverflowPrediction(Bin bin, @FutureOrPresent Date predictedFullDate, @NotNull @Min(0) Integer daysUntilFull,
            UsagePatternModel modelUsed, Instant generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }

    public Bin getBin() {
        return bin;
    }

    public Date getPredictedFullDate() {
        return predictedFullDate;
    }

    public Integer getDaysUntilFull() {
        return daysUntilFull;
    }

    public UsagePatternModel getModelUsed() {
        return modelUsed;
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public void setPredictedFullDate(Date predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }

    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }

    public void setModelUsed(UsagePatternModel modelUsed) {
        this.modelUsed = modelUsed;
    }

    public void setGeneratedAt(Instant generatedAt) {
        this.generatedAt = generatedAt;
    }
    
}
