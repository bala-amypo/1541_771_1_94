package com.example.demo.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bin;

public interface BinRepository extends JpaRepository<Bin,Long> {

    // boolean existsByIdentifier(String identifier);

    // List<Bin> findByActiveTrue();
}
