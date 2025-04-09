package com.kau.capstone.entity.walk.repository;

import com.kau.capstone.entity.pet.Pet;
import com.kau.capstone.entity.walk.Walk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk,Long> {
    Optional<Walk> findTopByPetOrderByDataIntDtDesc(Pet pet);

    List<Walk> findByPetAndDataIntDt(Pet pet, LocalDate dataIntDt);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND YEAR(w.dataIntDt) = :year AND MONTH(w.dataIntDt) = :month")
    List<Walk> findByPetAndYearAndMonth(@Param("pet") Pet pet, @Param("year") int year, @Param("month") int month);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet AND w.dataIntDt BETWEEN :startDate AND :endDate")
    List<Walk> findByPetAndDateBetween(@Param("pet") Pet pet, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT w FROM Walk w WHERE w.pet = :pet ORDER BY w.dataIntDt DESC")
    List<Walk> findTop5ByPetOrderByDataIntDtDesc(@Param("pet") Pet pet, Pageable pageable);
}

