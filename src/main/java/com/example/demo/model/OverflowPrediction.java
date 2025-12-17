package com.example.demo.model;

import java.security.Timestamp;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
@Entity
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Bin bin;
    @FutureOrPresent
    private Date predictedFullDate;
    @NotNull
    @PositiveOrZero
    private Integer daysUntilFull;
    private UsagePatternModel modelUesd;
    @CreationTimestamp
    private Timestamp generatedAt;
    
    public OverflowPrediction() {
    }
    
    public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull, UsagePatternModel modelUesd,
            Timestamp generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUesd = modelUesd;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Bin getBin() {
        return bin;
    }
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    public Date getPredictedFullDate() {
        return predictedFullDate;
    }
    public void setPredictedFullDate(Date predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }
    public Integer getDaysUntilFull() {
        return daysUntilFull;
    }
    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }
    public UsagePatternModel getModelUesd() {
        return modelUesd;
    }
    public void setModelUesd(UsagePatternModel modelUesd) {
        this.modelUesd = modelUesd;
    }
    public Timestamp getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(Timestamp generatedAt) {
        this.generatedAt = generatedAt;
    }
    
}
