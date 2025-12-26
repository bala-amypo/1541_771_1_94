package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody Zone zone) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Zone created", zoneService.createZone(zone))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody Zone zone) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Zone updated", zoneService.updateZone(id, zone))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> get(@PathVariable Long id) {
        return ResponseEntity.ok(zoneService.getZoneById(id));
    }

    @GetMapping
    public ResponseEntity<List<Zone>> getAll() {
        return ResponseEntity.ok(zoneService.getAllZones());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse> deactivate(@PathVariable Long id) {
        zoneService.deactivateZone(id);
        return ResponseEntity.ok(new ApiResponse(true, "Zone deactivated"));
    }
}
