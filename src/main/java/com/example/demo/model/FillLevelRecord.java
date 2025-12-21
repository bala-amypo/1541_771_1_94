package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Bin bin;

    @NotNull
    @Min(0)
    @Max(100)
    private Double fillPercentage;

    @PastOrPresent
    private Instant recordedAt;

    private Boolean isWeekend;

    public FillLevelRecord() {
    }

    public FillLevelRecord(Bin bin, @NotNull @Min(0) @Max(100) Double fillPercentage, @PastOrPresent Instant recordedAt,
            Boolean isWeekend) {
        this.bin = bin;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
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

    public Instant getRecordedAt() {
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

    public void setRecordedAt(Instant recordedAt) {
        this.recordedAt = recordedAt;
    }

    public void setIsWeekend(Boolean isWeekend) {
        this.isWeekend = isWeekend;
    }
    
}
