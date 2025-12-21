package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String zoneName;
    private String description;
    @Column(nullable = false)
    private Boolean active;
    
    public Zone() {
    }
    public Zone(String zoneName, String description, Boolean active) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public String getZoneName() {
        return zoneName;
    }
    public String getDescription() {
        return description;
    }
    public Boolean getActive() {
        return active;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    
}
