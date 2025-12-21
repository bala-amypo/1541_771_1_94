// package com.example.demo.model;

// import java.time.Instant;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;
// import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Positive;

// @Entity
// @Table(
//     name = "bins",
//     uniqueConstraints = @UniqueConstraint(columnNames = "identifier")
// )
// public class Bin {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank
//     @Column(nullable = false, unique = true)
//     private String identifier;

//     private String locationDescription;

//     private Double latitude;
//     private Double longitude;

//     @ManyToOne
//     @JoinColumn(name = "zone_id")
//     private Zone zone;

//     @NotNull
//     @Positive
//     private Double capacityLiters;

//     @Column(nullable = false)
//     private Boolean active = true;

//     @Column(updatable = false)
//     private Instant createdAt;

//     private Instant updatedAt;

//     @PrePersist
//     public void onCreate() {
//         createdAt = Instant.now();
//         updatedAt = Instant.now();
//     }

//     @PreUpdate
//     public void onUpdate() {
//         updatedAt = Instant.now();
//     }

//     public Bin() {
//     }

//     public Bin(@NotBlank String identifier, String locationDescription, Double latitude, Double longitude, Zone zone,
//             @NotNull @Positive Double capacityLiters, Boolean active, Instant createdAt, Instant updatedAt) {
//         this.identifier = identifier;
//         this.locationDescription = locationDescription;
//         this.latitude = latitude;
//         this.longitude = longitude;
//         this.zone = zone;
//         this.capacityLiters = capacityLiters;
//         this.active = active;
//         this.createdAt = createdAt;
//         this.updatedAt = updatedAt;
//     }

//     public Long getId() {
//         return id;
//     }

//     public String getIdentifier() {
//         return identifier;
//     }

//     public String getLocationDescription() {
//         return locationDescription;
//     }

//     public Double getLatitude() {
//         return latitude;
//     }

//     public Double getLongitude() {
//         return longitude;
//     }

//     public Zone getZone() {
//         return zone;
//     }

//     public Double getCapacityLiters() {
//         return capacityLiters;
//     }

//     public Boolean getActive() {
//         return active;
//     }

//     public Instant getCreatedAt() {
//         return createdAt;
//     }

//     public Instant getUpdatedAt() {
//         return updatedAt;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setIdentifier(String identifier) {
//         this.identifier = identifier;
//     }

//     public void setLocationDescription(String locationDescription) {
//         this.locationDescription = locationDescription;
//     }

//     public void setLatitude(Double latitude) {
//         this.latitude = latitude;
//     }

//     public void setLongitude(Double longitude) {
//         this.longitude = longitude;
//     }

//     public void setZone(Zone zone) {
//         this.zone = zone;
//     }

//     public void setCapacityLiters(Double capacityLiters) {
//         this.capacityLiters = capacityLiters;
//     }

//     public void setActive(Boolean active) {
//         this.active = active;
//     }

//     public void setCreatedAt(Instant createdAt) {
//         this.createdAt = createdAt;
//     }

//     public void setUpdatedAt(Instant updatedAt) {
//         this.updatedAt = updatedAt;
//     }
    
// }

