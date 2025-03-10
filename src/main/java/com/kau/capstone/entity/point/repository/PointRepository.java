package com.kau.capstone.entity.point.repository;

import com.kau.capstone.entity.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
