package com.kau.capstone.domain.food.repository;

import com.kau.capstone.domain.food.entity.Food;
import com.kau.capstone.domain.food.exception.FoodNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    default Food getById(long id){
        return findById(id).orElseThrow(FoodNotFoundException::new);
    }
}
