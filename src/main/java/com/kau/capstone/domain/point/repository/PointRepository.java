package com.kau.capstone.domain.point.repository;

import com.kau.capstone.domain.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
