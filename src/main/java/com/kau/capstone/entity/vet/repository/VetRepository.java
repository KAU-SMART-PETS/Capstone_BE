package com.kau.capstone.entity.vet.repository;

import com.kau.capstone.entity.vet.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VetRepository extends JpaRepository<Vet, Long> {

    @Query(value = "SELECT v.* " +
            "FROM vet v " +
            "ORDER BY (6371 * ACOS(COS(RADIANS(:userLatitude)) * COS(RADIANS(v.latitude)) * " +
            "COS(RADIANS(v.longitude) - RADIANS(:userLongitude)) + " +
            "SIN(RADIANS(:userLatitude)) * SIN(RADIANS(v.latitude)))) ASC " +
            "LIMIT 20", nativeQuery = true)
    List<Vet> findNearestVets(@Param("userLatitude") double userLatitude,
                              @Param("userLongitude") double userLongitude);
}
