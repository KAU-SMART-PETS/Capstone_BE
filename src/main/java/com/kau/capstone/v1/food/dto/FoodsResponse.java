package com.kau.capstone.v1.food.dto;

import com.kau.capstone.entity.food.Food;
import lombok.Builder;

import java.util.List;

@Builder
public record FoodsResponse(
        List<FoodResponse> foods
) {

    public static FoodsResponse toResponse(List<Food> foods) {
        List<FoodResponse> foodResponses = foods.stream()
                .map(FoodResponse::toResponse)
                .toList();

        return new FoodsResponse(foodResponses);
    }
}
