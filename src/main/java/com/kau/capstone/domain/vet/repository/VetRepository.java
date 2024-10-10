package com.kau.capstone.domain.vet.repository;

import com.kau.capstone.domain.vet.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
