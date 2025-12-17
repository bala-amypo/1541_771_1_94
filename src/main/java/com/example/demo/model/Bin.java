package com.example.demo.model;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique =true)
    private String identifier;
    private String locationDescription;
    private Double latitude;
    private Double longitude;
    private Zone zone;
    @NotNull
    @Positive(message = "capacityLiters must be greater than 0")
    private Double capacityLiters;
    @Column(nullable = false)
    private Boolean active;
    @CreationTimestamp
    private Timestamp createdAt;
    @CreationTimestamp
    private Timestamp updatedAt;
    
    public Bin() {
    }
    public Bin(String identifier, String locationDescription, Double latitude, Double longitude, Zone zone,
            Double capacityLiters, Boolean active, Timestamp createdAt, Timestamp updatedAt) {
        this.identifier = identifier;
        this.locationDescription = locationDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zone = zone;
        this.capacityLiters = capacityLiters;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Long getId() {
        return id;
    }
    public String getIdentifier() {
        return identifier;
    }
    public String getLocationDescription() {
        return locationDescription;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public Zone getZone() {
        return zone;
    }
    public Double getCapacityLiters() {
        return capacityLiters;
    }
    public Boolean getActive() {
        return active;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    public void setCapacityLiters(Double capacityLiters) {
        this.capacityLiters = capacityLiters;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    

}
