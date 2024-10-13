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
        return FoodResponse.builder()
                .id(food.getId())
                .imageUrl(food.getImageUrl())
                .price(food.getPrice())
                .build();
    }
}
