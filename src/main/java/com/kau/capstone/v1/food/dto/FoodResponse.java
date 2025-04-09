package com.kau.capstone.v1.food.dto;

import com.kau.capstone.entity.food.Food;
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
