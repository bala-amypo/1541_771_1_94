package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    Optional<OverflowPrediction> findTop1ByBinOrderByGeneratedAtDesc(Bin bin);

    List<OverflowPrediction> findByBinId(Long binId);

    @Query("""
        SELECT p FROM OverflowPrediction p
        WHERE p.bin.zone.id = :zoneId
        AND p.generatedAt = (
            SELECT MAX(p2.generatedAt)
            FROM OverflowPrediction p2
            WHERE p2.bin.id = p.bin.id
        )
    """)
    List<OverflowPrediction> findLatestByZoneId(Long zoneId);
}
