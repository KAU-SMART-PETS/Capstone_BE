package com.kau.capstone.v2.food.dto.response;

import com.kau.capstone.entity.food.Food;

public record FoodResponseV2(
        Long id,
        String imageUrl,
        Long price
) {

    public static FoodResponseV2 of(Food food) {
        return new FoodResponseV2(
                food.getId(),
                food.getImageUrl(),
                food.getPrice()
        );
    }
}
