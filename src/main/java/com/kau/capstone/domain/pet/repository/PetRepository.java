package com.kau.capstone.domain.pet.repository;

import com.kau.capstone.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
