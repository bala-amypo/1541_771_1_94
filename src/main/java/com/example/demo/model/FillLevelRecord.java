package com.example.demo.model;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Bin bin;
    @NotNull
    @Size(min=0,max=100)
    private Double fillPercentage;
    @Past
    private Timestamp recordedAt;
    private  Boolean isWeekend;
    
    public FillLevelRecord(Bin bin, Double fillPercentage, Timestamp recordedAt, Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
        this.isWeekend = isWeekend;
    }
    public FillLevelRecord(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }
    public Long getId() {
        return id;
    }
    public Bin getBin() {
        return bin;
    }
    public Double getFillPercentage() {
        return fillPercentage;
    }
    public Timestamp getRecordedAt() {
        return recordedAt;
    }
    public Boolean getIsWeekend() {
        return isWeekend;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    public void setFillPercentage(Double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }
    public void setRecordedAt(Timestamp recordedAt) {
        this.recordedAt = recordedAt;
    }
    public void setIsWeekend(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    
}
