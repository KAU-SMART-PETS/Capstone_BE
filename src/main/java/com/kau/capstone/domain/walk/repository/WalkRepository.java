package com.kau.capstone.domain.walk.repository;

import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.walk.entity.Walk;
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
}

