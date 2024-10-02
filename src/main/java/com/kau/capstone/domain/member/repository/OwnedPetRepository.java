package com.kau.capstone.domain.member.repository;

import com.kau.capstone.domain.member.entity.pet.OwnedPet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnedPetRepository extends JpaRepository<OwnedPet, Long> {
}
