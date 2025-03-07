package com.kau.capstone.domain.food.dto;

import com.kau.capstone.domain.food.entity.Food;
import lombok.Builder;

@Builder
public record FoodResponse(
        Long id,
        String imageUrl,
        Long price
) {

    public static FoodResponse toResponse(Food food) {
        return new FoodResponse(
                food.getId(),
                food.getImageUrl(),
                food.getPrice()
        );
    }
}
