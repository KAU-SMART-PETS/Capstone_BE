package com.kau.capstone.entity.food.repository;

import com.kau.capstone.entity.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
