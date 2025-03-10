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
        return FoodResponse.builder()
                .id(food.getId())
                .imageUrl(food.getImageUrl())
                .price(food.getPrice())
                .build();
    }
}
