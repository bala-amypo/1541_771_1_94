package com.example.demo.model;

import java.security.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity
public class UsagePatternModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Bin bin;
    @NotNull
    private Double avgDailyIncreaseWeekday;
    @NotNull
    private Double avgDailyIncreaseWeekEND;
    @UpdateTimestamp
    private Timestamp lastUpdated;
    public UsagePatternModel() {
    }
    public UsagePatternModel(Bin bin, Double avgDailyIncreaseWeekday, Double avgDailyIncreaseWeekEND,
            Timestamp lastUpdated) {
        this.bin = bin;
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
        this.avgDailyIncreaseWeekEND = avgDailyIncreaseWeekEND;
        this.lastUpdated = lastUpdated;
    }
    public Long getId() {
        return id;
    }
    public Bin getBin() {
        return bin;
    }
    public Double getAvgDailyIncreaseWeekday() {
        return avgDailyIncreaseWeekday;
    }
    public Double getAvgDailyIncreaseWeekEND() {
        return avgDailyIncreaseWeekEND;
    }
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    public void setAvgDailyIncreaseWeekday(Double avgDailyIncreaseWeekday) {
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
    }
    public void setAvgDailyIncreaseWeekEND(Double avgDailyIncreaseWeekEND) {
        this.avgDailyIncreaseWeekEND = avgDailyIncreaseWeekEND;
    }
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
}
