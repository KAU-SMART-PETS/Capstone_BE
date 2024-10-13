package com.kau.capstone.domain.food.dto;

import com.kau.capstone.domain.food.entity.Food;
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

        return FoodsResponse.builder()
                .foods(foodResponses)
                .build();
    }
}
