package com.kau.capstone.domain.hospital.repository;

import com.kau.capstone.domain.hospital.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
