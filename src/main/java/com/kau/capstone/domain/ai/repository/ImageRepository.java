package com.kau.capstone.domain.ai.repository;

import com.kau.capstone.domain.ai.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
