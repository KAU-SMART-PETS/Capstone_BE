package com.kau.capstone.entity.food.repository;

import com.kau.capstone.entity.food.Food;
import com.kau.capstone.v2.food.exception.FoodNotFoundExceptionV2;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    default @NonNull Food getById(@NonNull Long id){
        return findById(id).orElseThrow(FoodNotFoundExceptionV2::new);
    }
}
