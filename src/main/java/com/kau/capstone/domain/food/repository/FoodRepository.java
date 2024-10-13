package com.kau.capstone.domain.food.repository;

import com.kau.capstone.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
