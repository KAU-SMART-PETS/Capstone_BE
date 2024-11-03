package com.kau.capstone.domain.walk.repository;

import com.kau.capstone.domain.walk.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRepository extends JpaRepository<Walk,Long> {
}
