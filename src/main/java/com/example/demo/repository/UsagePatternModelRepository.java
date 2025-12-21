package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelRepository extends JpaRepository<UsagePatternModel, Long> {

    // @Query("""
    //     SELECT u FROM UsagePatternModel u
    //     WHERE u.bin.id = :binId
    //     ORDER BY u.lastUpdated DESC
    // """)
    Optional<UsagePatternModel> findLatestByBinId(Long binId);
}
