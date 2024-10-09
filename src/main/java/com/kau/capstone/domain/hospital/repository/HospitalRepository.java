package com.kau.capstone.domain.hospital.repository;

import com.kau.capstone.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
