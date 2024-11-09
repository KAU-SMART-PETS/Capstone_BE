package com.kau.capstone.domain.walk.repository;

import com.kau.capstone.domain.pet.entity.Pet;
import com.kau.capstone.domain.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk,Long> {
    Optional<Walk> findTopByPetOrderByDataIntDtDesc(Pet pet);
}

